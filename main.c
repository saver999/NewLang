#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
char supporto[100];
void elaboraScelta(int operazione){
float result,numB,numA;
numA = 0.0;
numB = 0.0;
result = 0.0;
printf("inserisci il primo numero");
scanf("%f",&numA);printf("inserisci il secondo numero");
scanf("%f",&numB);if(operazione == 1){
somma(&numA,numB,result);
printf("%s %f 
", "il risultato della somma  è :  ", result)}
if(operazione == 2){
sottrazione(&numA,numB,result);
printf("%s %f 
", "il risultato della sottrazione  è :  ", result)}
if(operazione == 3){
moltiplicazione(&numA,numB,result);
printf("%s %f 
", "il risultato della moltiplicazione  è :  ", result)}
if(operazione == 4){
if(numB != 0){
divisione(&numA,numB,result);
}
else{
printf("%s 
", "Divisione impossibile, il denomitaore non può essere 0")}
}
}
void somma(float numB,float numA,float* result){
result = numA + numB;
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
void test(){
int operazione,condizione=1;
while (condizione == 1){
printf("%s 
", "quale operazione aritmetica vuoi scegliere?")printf("%s 
", "se vuoi fare la somma scrivi 1 ")printf("%s 
", "se vuoi fare la sottrazione scrivi 2 ")printf("%s 
", "se vuoi fare la moltiplicazione scrivi 3 ")printf("%s 
", "se vuoi fare la divisione  scrivi 4 ")printf("inserisci la tua scelta qui (1,2,3,4)");
scanf("%d",&operazione);if(operazione == 1 || operazione == 2 || operazione == 3 || operazione == 4){
elaboraScelta(operazione);
}
printf("%s 
", "vuoi continuare? scrivi 1, vuoi stopparti scrivi 0")printf("null");
scanf("%d",&condizione);}
}
int main(){
test();
return 0;
}
