import csv 			#Necesaria para leer el datatset
import random		#Necesaria para sacar numeros aleatorios
import math			#Necesaria para calcular distancia euclidiana
import operator		#Necesaria para tomar los valores de un arreglo en una posición exacta
 
#Método que lee el dataset y lo divide en set de entrenamiento y prueba de forma aleatoria
def loadDataset(filename, split, trainingSet=[] , testSet=[]):
	with open(filename, 'r') as csvfile:                        #Se abre el archivo
	    lines = csv.reader(csvfile)								#Se lee el dataset usando la libreria csv
	    dataset = list(lines)									#Se le asignan las líneas del dataset a la variable dataset
	    for x in range(len(dataset)):							#Se itera por el total de líneas del dataset
	        for y in range(12):                                 #Itera dependiendo del número de atributos del dataset
	            dataset[x][y] = float(dataset[x][y])			#Cambia el tipo de dato de string a entero
	        if random.random() < split:							#Divide de forma aleatoria el dataset en entrenamiento/prueba
	            trainingSet.append(dataset[x])					#Asigna la linea a set de entrenamiento
	        else:
	            testSet.append(dataset[x])						#Asigna la linea a set de prueba

#Método que calcula distancia euclidiana entre dos instancias 
def euclideanDistance(instance1, instance2, length):
	distance = 0
	for x in range(length):
		distance += pow((instance1[x] - instance2[x]), 2) 		
	return math.sqrt(distance)									#Raiz cuadrada de la suma del cuadrado de la diferencia entre dos números

#Método que encuentra los 'k' vecinos mas cercanos
def getNeighbors(trainingSet, testInstance, k):
	distances = []														#Crea la lista de distancias
	length = len(testInstance)-1										#Calcula el total de atributos de la instancia de prueba quitando la etiqueta
	for x in range(len(trainingSet)):									#Itera por el total de líneas en el set de entrenamiento
		dist = euclideanDistance(testInstance, trainingSet[x], length)	#Calcula la distancia euclidiana
		distances.append((trainingSet[x], dist))						#Agrega la distancia a la lista y la instancia del set de entrenamiento que le corresponde
	distances.sort(key=operator.itemgetter(1))							#Ordena la lista de distancias de menor a mayor distancia (ascendente)
	neighbors = []														#Crea el arreglo de vecinos
	for x in range(k):													#Itera dependiendo de k,ejemplo si K=1 solo tomaria al primero de la lista
		neighbors.append(distances[x][0])								#Agrega el vecino a la lista(se usa [x][0] para solo agregar al vecino)
	return neighbors 													#Regresa los vecinos

#Método usado para elegir la clasificación con más 'votos' entre los vecinos
def getResponse(neighbors):
	classVotes = {}														#Crea un diccionario de votos, ejemplo {5.0:1,7.0:2}
	for x in range(len(neighbors)):										#Itera dependiendo del total de vecinos
		response = neighbors[x][-1]										#Toma la etiqueta(la clasificación) de cada vecino
		if response in classVotes:										#Hace un conteo de los votos de cada clasificación de los vecinos
			classVotes[response] += 1									
		else:
			classVotes[response] = 1
	sortedVotes = sorted(classVotes.items(), key=operator.itemgetter(1), reverse=True) #Ordena los votos de forma descendente
	return sortedVotes[0][0]											#Regresa la clasificación con más votos/la primera del diccionario

#Método que calcula la precisión de las predicciones
def getAccuracy(testSet, predictions):
	correct = 0															#Contador de predicciones correctas
	for x in range(len(testSet)):										#Itera todo el set de prueba
		if testSet[x][-1] == predictions[x]:							#Si la clasificación de la instancia es igual a la predicción
			correct += 1												#Se aumentan las predicciones correctas
	return (correct/float(len(testSet))) * 100.0						#Calcula el % de precisión --- (correctas/total)*100
	
def main():
	#Preparando datos
	trainingSet=[]
	testSet=[]
	split = 0.8															#División de los datos entre prueba y entrenamiento
	loadDataset('winequality-red.csv', split, trainingSet, testSet)
	print ('Train set: ' + repr(len(trainingSet)))
	print ('Test set: ' + repr(len(testSet)))
	#Generar predicciones
	predictions=[]
	k = 3 																#Número de vecinos a tomar en cuenta
	for x in range(len(testSet)):
		neighbors = getNeighbors(trainingSet, testSet[x], k)
		result = getResponse(neighbors)
		predictions.append(result)
		print('> predicted=' + repr(result) + ', actual=' + repr(testSet[x][-1]))
	accuracy = getAccuracy(testSet, predictions)
	print('Accuracy: ' + repr(accuracy) + '%')
main()