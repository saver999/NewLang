#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
void main2();

char supporto[100];

void main2(){
	int f,i,penultimo,ultimo;
	penultimo = 0;
		printf("%d \n", penultimo);
	ultimo = 1;
		printf("%d \n", ultimo);
	for (int i = 2;i <= 100;i++){
	f = ultimo + penultimo;
		printf("%d \n", f);
	penultimo = ultimo;
	ultimo = f;
}
}
int main(){
main2();
return 0;
}
