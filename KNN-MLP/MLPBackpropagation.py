from random import randrange								#Usado para darle un rango a los numeros pseualeatorios
from random import random 									#Usado para crear numeros psudoaleatorios
from csv import reader 										#Usado para leer el archivo(csv,data,etc.)
from math import exp 										#Usado para calcular e^x

#Método que lee el dataset
def load_csv(filename):
	dataset = list()										#Crea la lista donde se guardaran los datos del dataset
	with open(filename, 'r') as file:						#Abre el dataset
		csv_reader = reader(file)							#Lee el data set
		for row in csv_reader:								#Itera hasta acabar los renglones del archivo
			if not row:
				continue
			dataset.append(row)								#Guarda los renglones leidos
	return dataset 											#Regresa el dataset leido

#Método que convierte las columnas string en flotantes
def str_column_to_float(dataset, column):
	for row in dataset:										#Itera para cada renglon del dataset
		row[column] = float(row[column].strip())			#Convierte a flotante el la columna y le quita los espacios con el .strip

#Método que convierte las columnas de clase en enteros(cambia etiquetas string por enteros)
def str_column_to_int(dataset, column):
	class_values = [row[column] for row in dataset]
	unique = set(class_values)
	lookup = dict()
	for i, value in enumerate(unique):
		lookup[value] = i
	for row in dataset:
		row[column] = lookup[row[column]]
	return lookup

#Método que busca los valores maximos y minimos de cada atributo
def dataset_minmax(dataset):
	minmax = list()
	stats = [[min(column), max(column)] for column in zip(*dataset)] #Itera para cada atributo del dataset sacando el max y min
	return stats 										#Regresa un arreglo de tuplas con el minimo y maximo de cada atributo
														# Ej. [[4.3, 7.9], [2.0, 4.4], [1.0, 6.9], [0.1, 2.5], [0, 2]]

#Metodo que reescala los atributos del dataset a 0-1
def normalize_dataset(dataset, minmax):
	for row in dataset:
		for i in range(len(row)-1):
			row[i] = (row[i] - minmax[i][0]) / (minmax[i][1] - minmax[i][0])

#Divide el dataset en 'k' partes
def cross_validation_split(dataset, n_folds):
	dataset_split = list()
	dataset_copy = list(dataset)
	fold_size = int(len(dataset) / n_folds)
	for i in range(n_folds):
		fold = list()
		while len(fold) < fold_size:
			index = randrange(len(dataset_copy))
			fold.append(dataset_copy.pop(index))
		dataset_split.append(fold)
	return dataset_split

#Método que calcula el porcentaje de precisión
def accuracy_metric(actual, predicted):
	correct = 0
	for i in range(len(actual)):
		if actual[i] == predicted[i]:
			correct += 1
	return correct / float(len(actual)) * 100.0

#Método que evalua el algoritmo usando la validación cruzada
def evaluate_algorithm(dataset, algorithm, n_folds, *args):
	folds = cross_validation_split(dataset, n_folds)
	scores = list()
	for fold in folds:
		train_set = list(folds)
		train_set.remove(fold)
		train_set = sum(train_set, [])
		test_set = list()
		for row in fold:
			row_copy = list(row)
			test_set.append(row_copy)
			row_copy[-1] = None
		predicted = algorithm(train_set, test_set, *args)
		actual = [row[-1] for row in fold]
		accuracy = accuracy_metric(actual, predicted)
		scores.append(accuracy)
	return scores

#Método que calcula la activación de la neurona
def activate(weights, inputs):
	activation = weights[-1]
	for i in range(len(weights)-1):
		activation += weights[i] * inputs[i]
	return activation

#Método que transfiere la activación de la neurona usando la funcion sigmoide
def transfer(activation):
	return 1.0 / (1.0 + exp(-activation)) 

#Método que propaga hacia delante las entradas por toda la red
def forward_propagate(network, row):
	inputs = row
	for layer in network:										#Itera en toda las capas de la red
		new_inputs = []											
		for neuron in layer:									#Itera en todas las neuronas de la capa
			activation = activate(neuron['weights'], inputs)	#Activa las neuronas
			neuron['output'] = transfer(activation)				#Transfiere la activacion usando la funcion sigmoide
			new_inputs.append(neuron['output'])					#La salida de una neurona es la entrada de la siguiente
		inputs = new_inputs
	return inputs

#Método que calcula la derivada de la funcion sigmoide
def transfer_derivative(output):
	return output * (1.0 - output)

#Método que propaga hacia atras el error y lo guarda en las neuronas
def backward_propagate_error(network, expected):
	for i in reversed(range(len(network))):										#Itera desde la ultima neurona hasta la primera
		layer = network[i]
		errors = list()
		if i != len(network)-1:													#Para todas las capas menos la ultima
			for j in range(len(layer)):
				error = 0.0
				for neuron in network[i + 1]:									#Itera en todas las neuronas para calcular el error(+1 es para agarrar los pesos de las ultimas)
					error += (neuron['weights'][j] * neuron['delta'])
				errors.append(error)
		else:																	#Para la ultima capa(salidas)
			for j in range(len(layer)):
				neuron = layer[j]
				errors.append(expected[j] - neuron['output'])					#Calcula error = esperado - salida
		for j in range(len(layer)):
			neuron = layer[j]
			neuron['delta'] = errors[j] * transfer_derivative(neuron['output']) #Se transfiere el error recalculandolo con la derivada

#Método que actuliza los pesos con error
def update_weights(network, row, l_rate):
	for i in range(len(network)):													#Itera por toda la red
		inputs = row[:-1]															#Agarra los atributos del dataset([:-1] para quitar la etiqueta)
		if i != 0:																	#No toma la primera capa
			inputs = [neuron['output'] for neuron in network[i - 1]]
		for neuron in network[i]:													#Itera en todas las neuronas de la capa
			for j in range(len(inputs)):											#Itera en las entradas de las neuronas
				neuron['weights'][j] += l_rate * neuron['delta'] * inputs[j]		#Recalcula el peso de las neuronas
																					#nuevo peso= peso + (ratio de aprendizaje*error*entrada que causo el error)
			neuron['weights'][-1] += l_rate * neuron['delta']						#Recalcula el ultimo peso(bias)

#Método que entrena una red en un número de epocas
def train_network(network, train, l_rate, n_epoch, n_outputs):
	for epoch in range(n_epoch):								#Itera para el número de epocas dadas
		print('Epoch: %i'% epoch)								#Imprime el contador de epocas
		#sum_error = 0
		for row in train:										#Itera para cada renglon de entrenamiento
			outputs = forward_propagate(network, row)			#Activa todas las neuronas y recive las salidas
			expected = [0 for i in range(n_outputs)]			#Inicializa los atributos con 0
			expected[row[-1]] = 1								#Inicializa las clasificaciones con 1
			#sum_error += sum([(expected[i]-outputs[i])**2 for i in range(len(expected))])
			backward_propagate_error(network, expected)			#Propaga el error
			update_weights(network, row, l_rate)				#Actualiza pesos
		#print('>Epoch=%d, Error=%.3f' % (epoch, sum_error))

#Método que inicializa la red
def initialize_network(n_inputs, n_hidden, n_outputs):
	network = list()
	hidden_layer = [{'weights':[random() for i in range(n_inputs + 1)]} for i in range(n_hidden)] #Crea los pesos de manera aleatoria por cada entrada más el bias
	network.append(hidden_layer)
	output_layer = [{'weights':[random() for i in range(n_hidden + 1)]} for i in range(n_outputs)]#Crea los pesos de manera aleatoria por cada capa oculta más el bias
	network.append(output_layer)
	return network

#Método que hace una prediccion de la red usando propagacion hacia adelante
def predict(network, row):
	outputs = forward_propagate(network, row)	#Arreglo con las probabilidades de que sea cierta clasificacion	
	return outputs.index(max(outputs))			#Regresa el valor con mas probabilidad

#Método de propagación hacia atras con gradiente descendente estocástico(epocas)
def back_propagation(train, test, l_rate, n_epoch, n_hidden):
	n_inputs = len(train[0]) - 1								#Calcula el numero de entradas con los atributos del dataset
	n_outputs = len(set([row[-1] for row in train]))			#Calcula el numero de clasificaciones(salidas/etiquetas)
	network = initialize_network(n_inputs, n_hidden, n_outputs) #Inicializa la red
	train_network(network, train, l_rate, n_epoch, n_outputs)	#Entrena la red
	predictions = list()
	for row in test:											#Itera por cada renglon en el set de prueba
		prediction = predict(network, row)						#Calcula las predicciones
		predictions.append(prediction)							#Guarda las predicciones
	return(predictions)											#Regresa las predicciones

#Cargar y preparar los datos
filename = 'transfusion.data'
dataset = load_csv(filename)
for i in range(len(dataset[0])-1):
	str_column_to_float(dataset, i)
#Convertir las etiquetas en enteros
str_column_to_int(dataset, len(dataset[0])-1)
#Normalizar variables
minmax = dataset_minmax(dataset)
normalize_dataset(dataset, minmax)
#Evaluar el algoritmo
n_folds = 2
l_rate = 0.2
n_epoch = 1000
n_hidden = 1
scores = evaluate_algorithm(dataset, back_propagation, n_folds, l_rate, n_epoch, n_hidden)
print('Scores: %s' % scores)
print('Mean Accuracy: %.3f%%' % (sum(scores)/float(len(scores))))