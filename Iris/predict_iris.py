import pandas as pd
import numpy as np
import tensorflow as tf

TRAIN_URL = "http://download.tensorflow.org/data/iris_training.csv"
TEST_URL = "http://download.tensorflow.org/data/iris_test.csv"
CSV_COLUMN_NAMES = ['SepalLength','SepalWidth', 'PetalLength','PetalWidth','Species']
HEADER = ['setosa','versicolor','virginica']
def load_data(label_name = 'Species'):
    """Parses the csv file in TRAIN_URL and TEST_URL"""

    #create a local copy of thw training set
    train_path = tf.keras.utils.get_file(fname=TRAIN_URL.split('/')[-1],origin = TRAIN_URL)

    #train_path now holds the pathname :
    train = pd.read_csv(filepath_or_buffer = train_path, names = CSV_COLUMN_NAMES, header=0)

    #train now holds a pandas DataFrame wich is data structure
    #analogous to a table

    #1.- Assign the DataFrame's lables (the right-most column) to train_label
    #2.- Delete (pop) the labels from the DataFrame
    #3.- Assign the remainder of the DataFrame to train_features
    train_features, train_labels = train, train.pop(label_name)
    # Apply the preceding logic to the test set
    test_path = tf.keras.utils.get_file(TEST_URL.split('/')[-1],TEST_URL)
    test = pd.read_csv(test_path,names=CSV_COLUMN_NAMES,header=0)
    test_features, test_labels = test, test.pop(label_name)

    #returns four dataframes
    return (train_features,train_labels) , (test_features,test_labels)

(train_feature, train_label), (test_feature,test_label) = load_data()

#Creating feature columns for all features
my_feature_columns = []
for key in train_feature.keys():
    my_feature_columns.append(tf.feature_column.numeric_column(key=key))
print(my_feature_columns)

classifier = tf.estimator.DNNClassifier(
    feature_columns=my_feature_columns,
    hidden_units=[10,10],
    n_classes=3
)

def train_input_fn(features,labels,batch_size):
    """This function will supply the training data"""
    dataset = tf.data.Dataset.from_tensor_slices((dict(features),labels))
    dataset = dataset.shuffle(buffer_size=1000).repeat(count=None).batch(batch_size)
    return dataset.make_one_shot_iterator().get_next()

classifier.train(
    input_fn=lambda:train_input_fn(train_feature,train_label,1000),
    steps=1000
)



def eval_input_fn(features,labels=None,batch_size=None):

    """An input function for evaluation or prediction"""
    if labels is None:
        #No labels, use only features
        inputs = features
    else:
        inputs = (features,labels)
    #Convert inputs to a tf.dataset obect
    dataset = tf.data.Dataset.from_tensor_slices(inputs)

    #batch the examples
    assert batch_size is not None, "Batch_size must not be None"
    dataset = dataset.batch(batch_size)

    #Return the read end of the pipeline 
    print("Before  something")
    return dataset.make_one_shot_iterator().get_next()
    
#Evaluate the model
predict_x = {
        'SepalLength': [5.1, 5.9, 6.9],
        'SepalWidth': [3.3, 3.0, 3.1],
        'PetalLength': [1.7, 4.2, 5.4],
        'PetalWidth': [0.5, 1.5, 2.1],
    }

predictions = classifier.predict(
    input_fn=lambda:eval_input_fn(predict_x,labels=None,batch_size=1000)
)

