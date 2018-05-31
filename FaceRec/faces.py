import numpy as np 
import cv2
import pickle
face1_cascade = cv2.CascadeClassifier('cascades/data/haarcascade_frontalface_alt2.xml')
#face2_cascade = cv2.CascadeClassifier('cascades/data/haarcascade_frontalface_alt.xml')
recognizer = cv2.face.LBPHFaceRecognizer_create()#Local Binary Patterns Histograms, metodo del opencv para reconocer 
#recognizer = cv2.createFisherFaceRecognizer()
recognizer.read("face-trainner.yml")
labels={}
#cargar el diccionario de etiquetas
with open("labels.pickle", 'rb') as f:
	og_labels=pickle.load(f)
	labels={v:k for k,v in og_labels.items()} #invertir el diccionario para agarrar la etiqueta

cap=cv2.VideoCapture(0)

while(True):
	ret,frame = cap.read()
	gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
	face1 = face1_cascade.detectMultiScale(gray,scaleFactor=1.1,minNeighbors=10)
	#face2 = face2_cascade.detectMultiScale(gray,scaleFactor=1.5,minNeighbors=5)

	#capturando la region de interes
	for (x,y,w,h) in face1:
		#print(x,y,w,h)
		roi_gray=gray[y:y+h,x:x+w]
		roi_color=frame[y:y+h,x:x+w]
		
		id_,conf=recognizer.predict(roi_gray)
		if conf>=50:
			#escribir etiqueta arriba de la cara
			font = cv2.FONT_HERSHEY_SIMPLEX
    		name = labels[id_]
    		color = (255, 255, 255) #blanco
    		stroke = 2
    		cv2.putText(frame, name, (x,y), font, 1, color, stroke, cv2.LINE_AA)
    	#crear el rectangulo de la cara
		color=(255,0,0)# BGR, azul
		stroke=2
		end_cord_x=x+w
		end_cord_y=y+h
		cv2.rectangle(frame,(x,y),(end_cord_x,end_cord_y),color,stroke)
	#camara
	cv2.imshow('frame',frame)
	if cv2.waitKey(20) & 0xFF == ord('q'): #cerrar con la q
		break

#tomar foto
cap.release()
cv2.destroyAllWindows()
