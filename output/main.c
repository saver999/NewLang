#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
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

void test(){
	int num,div_cor;
	bool e_primo;
	printf("Dare il numero da controllare e poi andare a capo");
	scanf("%d",&num);
	div_cor = 2;
	e_primo = true;
	if(num < 0){
		printf("%s \n", "il numero non è positivo!");
}
else{
	if(num > 1){
	   while (div_cor < num && e_primo){
}
}
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
