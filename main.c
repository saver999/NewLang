#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void elaboraScelta(int operazione){
floatresult,numB,numA;
numA = 0.0;
numB = 0.0;
result = 0.0;
printf("inserisci il primo numero");
scanf("%f",&numA);printf("inserisci il secondo numero");
scanf("%f",&numB);if(operazione == 1){
nullprintf("%s %f 
", "il risultato della somma  è :  ", result)}
if(operazione == 2){
nullprintf("%s %f 
", "il risultato della sottrazione  è :  ", result)}
if(operazione == 3){
nullprintf("%s %f 
", "il risultato della moltiplicazione  è :  ", result)}
if(operazione == 4){
if(numB != 0){
null}
else{
printf("%s 
", "Divisione impossibile, il denomitaore non può essere 0")}
}
}
void somma(float numB,float numA,float* result){
result = (numA + numB);
}
void sottrazione(float numB,float numA,float* result){
result = numA - numB;
}
void divisione(float numB,float numA,float* result){
result = numA / numB;
}
void moltiplicazione(float numB,float numA,float* result){
result = numA * numB;
}
