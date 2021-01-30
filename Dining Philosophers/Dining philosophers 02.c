#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int count = 0;
pthread_t p0;
pthread_t p1;
pthread_t p2;
pthread_t p3;
pthread_t p4;
pthread_mutex_t mutex[5] = {1,2,3,4,5};

void *jantar(void *tid){

	int meu_id = 0;
	int outro = 1;
	int filo =  * (int *) tid;

	while(meu_id != outro){

		pthread_mutex_lock(&mutex[filo]); 
		pthread_mutex_lock(&mutex[(filo+1)%5]);
		
		printf("Filósofo %d está comendo no momento %i\n", filo, count );
		sleep(2);															// Filósofo comendo
		count = count + 2;													// Seção crítica

		pthread_mutex_unlock(&mutex[filo]);
		pthread_mutex_unlock(&mutex[(filo+1)%5]);

		sleep(10);															// Filósofo pensando
		outro++;
		if(outro > 2)
			pthread_exit(0);
	}
}


int main(){

	void * thread_res;
	int status, i;
	int a = 1;
	int b = 2;
	int c = 3;
	int d = 4;
	int e = 5;
	pthread_mutex_init(&mutex[0],NULL);
	pthread_mutex_init(&mutex[1],NULL);
	pthread_mutex_init(&mutex[2],NULL);
	pthread_mutex_init(&mutex[3],NULL);
	pthread_mutex_init(&mutex[4],NULL);

	// Criando as threads
		status = pthread_create(&p0, NULL, jantar, &a);

		if (status != 0){
			printf("o pthread_create retornou o código de erro %d\n", status);
		}

		status = pthread_create(&p1, NULL, jantar, &b);

		if (status != 0){
			printf("o pthread_create retornou o código de erro %d\n", status);
		}

		status = pthread_create(&p2, NULL, jantar, &c);

		if (status != 0){
			printf("o pthread_create retornou o código de erro %d\n", status);
		}

		status = pthread_create(&p3, NULL, jantar, &d);

		if (status != 0){
			printf("o pthread_create retornou o código de erro %d\n", status);
		}

		status = pthread_create(&p4, NULL, jantar, &e);

		if (status != 0){
			printf("o pthread_create retornou o código de erro %d\n", status);
		}

	// Finalizando as threads	
		status = pthread_join (p0, &thread_res);

		if (status != 0){
			printf("o pthread_join retornou o código de erro %d\n", status);
		}

		status = pthread_join (p1, &thread_res);

		if (status != 0){
			printf("o pthread_join retornou o código de erro %d\n", status);
		}

		status = pthread_join (p2, &thread_res);

		if (status != 0){
			printf("o pthread_join retornou o código de erro %d\n", status);
		}

		status = pthread_join (p3, &thread_res);

		if (status != 0){
			printf("o pthread_join retornou o código de erro %d\n", status);
		}

		status = pthread_join (p4, &thread_res);

		if (status != 0){
			printf("o pthread_join retornou o código de erro %d\n", status);
		}

	return 0;
}