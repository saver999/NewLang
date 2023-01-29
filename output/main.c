#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
float somma(float a,float b);
float differenza(float a,float b);
float prodotto(float a,float b);
float divisione(float a,float b);
float potenza(float a,float b);
float succ_fibonacci(float i);
int menu();
void esempio();


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

float somma(float a,float b){
	return a + b;
}
float differenza(float a,float b){
	return a - b;
}
float prodotto(float a,float b){
	float prod=0;
	int i=1;
	   while (i <= b){
	prod = prod + a;
	i = i + 1;
}
	return prod;
}
float divisione(float a,float b){
	return a / b;
}
float potenza(float a,float b){
	return pow(a,b);
}
float succ_fibonacci(float i){
	if(i < 0){
	return 0;
}
else{
	if(i == 0){
	return 0;
}
else{
	if(i == 1){
	return 1;
}
}
}
	return succ_fibonacci(i - 1) + succ_fibonacci(i - 2);
}
int menu(){
	int op;
		printf("%s \n", "--------Menu--------");
		printf("%s \n", "2) Addizione");
		printf("%s \n", "3) Sottrazione");
		printf("%s \n", "4) Moltiplicazione");
		printf("%s \n", "5) Divisione");
		printf("%s \n", "6) Potenza");
		printf("%s \n", "7) Fibonacci");
	printf("Inserisci operazione:");
	scanf("%d",&op);
	   while (op < 2 || op > 7){
	printf("Operazione non valida [2-7], inserisci operazione:");
	scanf("%d",&op);
}
	return op;
}
void esempio(){
	int comando= -1,op;
	float b,a,risultato;
	   while (comando != 0){
		printf("%s \n", "1) Visualizza Menù");
		printf("%s \n", "0) Termina");
	printf("Inserisci comando:");
	scanf("%d",&comando);
	if(comando == 1){
	op = menu();
	if(op != 7){
	printf("Inserisci il primo intero:");
	scanf("%f",&a);
	printf("Inserisci il secondo intero:");
	scanf("%f",&b);
}
else{
	printf("Inserisci il primo intero:");
	scanf("%f",&a);
}
	if(op == 2){
	risultato = somma(a,b);
}
else{
	if(op == 3){
	risultato = differenza(a,b);
}
else{
	if(op == 4){
	risultato = prodotto(a,b);
}
else{
	if(op == 5){
	risultato = divisione(a,b);
}
else{
	if(op == 6){
	risultato = potenza(a,b);
}
else{
	risultato = succ_fibonacci(a);
}
}
}
}
}
		printf("%s \n", strcat(strcpy(supporto,"Il risultato dell'operazione scelta è :"),conversioneFloat(risultato)));
}
}
		printf("%s \n", "Ciao");
	}
int main(){
int intero=0;
char carattere=' ';
float float1=0;
char *stringa="";
bool booleano=false;
esempio();
return 0;
}
