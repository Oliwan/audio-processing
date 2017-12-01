import scipy.io
matlab_export = scipy.io.loadmat('audio_data.mat')

data = matlab_export['X']

matlab_labels = matlab_export['Y']
labels = []

for l in matlab_labels:
    labels.append(l[0][0]) #sto prendendo solo T o W
    #labels.append(l[1][0]) #sto prendendo solo il sistema operativo
    #labels.append(l[0][0]+l[1][0]) #sto prendendo tutto insieme

from sklearn import neighbors
from sklearn.neighbors import NearestNeighbors
import numpy as np

#definisco il modello
n_neighbors = 1 #questo e' un parametro su cui giocare
model = neighbors.KNeighborsClassifier(n_neighbors,metric='euclidean',weights='distance')

# Per validare l'algoritmo di classificazione sui dati
# La crossvalidazione fa automaticamente le seguenti operazioni
# 1. (Prima lo mescola) Divide il dataset a seconda del parametro cv - ad esempio per cv=2 50% 
# del dataset e' di training (cioe su cui fa fit
# e 50% di dataset e' di test cioe e' sconosciuto al classificatore) e sono mutuamente esclusivi
# 2. Fa il fit del classificatore Knn sulla parte di training e testa il restante 50% di campioni su quel classificatore
# 3. Estrae una misura di accuracy che e' pari a Numero di campioni classificati correttamente / numero totale 
# di campioni (del test)

# cv = 2 vuol dire che lo fa 2 volte: prima il primo 50% e' train e poi diventa train il secondo 50%
from sklearn import cross_validation
scores = cross_validation.cross_val_score(model, np.asarray(data), np.asarray(labels), cv=2)
print scores[0:5]

print("Accuracy: %0.2f (+/- %0.2f)" % (scores[0:5].mean(), scores[0:5].std() * 2))



# a mano posso io dire su quali dati effettuare il fit (nell'esempio a seguire e' tutto il dataset)
# posso testare il singolo dato con il model.predict

# TODO: divisione di data in training e test manualmente
#model.fit(np.asarray(data), np.asarray(labels))
#model.predict(data[28]) == labels[28]

