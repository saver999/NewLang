#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
void elaboraScelta(int operazione);
void somma(float numA,float numB,float* result);
void sottrazione(float numA,float numB,float* result);
void divisione(float numA,float numB,float* result);
void moltiplicazione(float numA,float numB,float* result);
void mol(float numA,float numB,float* result);
void test();


char  *conversioneFloat(float number){
  
 char *buf = malloc(10*sizeof(char));
 sprintf (buf, "%f", number);
  return buf;
}
char * conversioneInt(int number){
 char *buf = malloc(10*sizeof(char));
 sprintf (buf, "%d", number);
  return buf;
}char supporto[100];

	int operazione;
	int prova1;
	bool  ala = true;
	int numC=0;
void elaboraScelta(int operazione){
	float numA,numB,result;
	numA = 0;
numB = 0;
result = 0;
	printf("inserisci il primo numero");
	scanf("%f",&numA);
	printf("inserisci il secondo numero");
	scanf("%f",&numB);
	if(operazione == 1){
	somma(numA,numB,&result);
		printf("%s \n", strcat(strcpy(supporto,"il risultato della somma  è :  "),conversioneFloat(result)));
}
	if(operazione == 2){
	sottrazione(numA,numB,&result);
		printf("%s \n", strcat(strcpy(supporto,"il risultato della sottrazione  è :  "),conversioneFloat(result)));
}
	if(operazione == 3){
	moltiplicazione(numA,numB,&result);
		printf("%s \n", strcat(strcpy(supporto,"il risultato della moltiplicazione  è :  "),conversioneFloat(result)));
}
	if(operazione == 4){
	if(numB != 0){
	divisione(numA,numB,&result);
}
else{
		printf("%s \n", "Divisione impossibile, il denomitaore non può essere 0");
}
}
}
void somma(float numA,float numB,float* result){
	float numC=0;
	*result = numA + numB;
}
void sottrazione(float numA,float numB,float* result){
	*result = numA - numB;
}
void divisione(float numA,float numB,float* result){
	*result = numA / numB;
}
void moltiplicazione(float numA,float numB,float* result){
	*result = numA * numB;
}
void mol(float numA,float numB,float* result){
	*result = numA * numB;
	}
void test(){
	int operazione,condizione=1;
	int prova1=1;
	   while (condizione == 1){
		printf("%s \n", "quale operazione aritmetica vuoi scegliere?");
		printf("%s \n", "se vuoi fare la somma scrivi 1 ");
		printf("%s \n", "se vuoi fare la sottrazione scrivi 2 ");
		printf("%s \n", "se vuoi fare la moltiplicazione scrivi 3 ");
		printf("%s \n", "se vuoi fare la divisione  scrivi 4 ");
	printf("inserisci la tua scelta qui (1,2,3,4)");
	scanf("%d",&operazione);
	if(operazione == 1 || operazione == 2 || operazione == 3 || operazione == 4){
	elaboraScelta(operazione);
}
		printf("%s \n", "vuoi continuare? scrivi 1, vuoi stopparti scrivi 0");
		scanf("%d",&condizione);
}
}
int main(){
int intero=0;
char carattere=' ';
float float1=0;
char *stringa="";
bool booleano=false;
test();
return 0;
}
