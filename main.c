#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
char*  test1(int a,char * refstringa,char * stringa);
void test();

char supporto[100];

char*  test1(int a,char * refstringa,char * stringa){
	refstringa = strcat(strcpy(supporto,stringa)," napolitano");
	return refstringa;
}
void test(){
	char *stringa="saverio";
	char *stringa2;
	stringa2 = test1(1,stringa2,stringa);
		printf("%s %s \n", "la stringa concatenata è :  ", stringa2);
}
int main(){
test();
return 0;
}
