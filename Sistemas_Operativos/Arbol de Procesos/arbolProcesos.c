#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define TAM 10
int main(void){
	pid_t id_proc, id_proc2, id_proc3, pid, ppid;
	int n=10, i,j, k, estado;
	printf("________________________________________________________________________________________\n");
	for(i=0; i<n; i++){
		if((id_proc=fork())==0){
			pid = getpid();
			printf("\nSoy el proceso padre numero: %d\n",pid);
			if(i == (TAM-n)){ 	//primer rama
				//printf("\n");
				for(j=0; j<n; j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t1.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-1)){
							//printf("\n");
							for(k=0; k<n; k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t1.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-1))){ 	//segunda rama
				//printf("\n");
				for(j=0; j<(n-1); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t2.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-2)){
							//printf("\n");
							for(k=0; k<(n-1); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t2.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-2))){ 	//tercer rama
				//printf("\n");
				for(j=0; j<(n-2); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t3.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-3)){
							//printf("\n");
							for(k=0; k<(n-2); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t3.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;				
					}
				}
			}
			else if(i == (TAM-(n-3))){ 	//cuarta rama
				//printf("\n");
				for(j=0; j<(n-3); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t4.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-4)){
							//printf("\n");
							for(k=0; k<(n-3); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t4.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-4))){ 	//quinta rama
				//printf("\n");
				for(j=0; j<(n-4); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t5.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-5)){
							//printf("\n");
							for(k=0; k<(n-4); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t5.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-5))){ 	//sexta rama
				//printf("\n");
				for(j=0; j<(n-5); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t6.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-6)){
							//printf("\n");
							for(k=0; k<(n-5); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t6.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-6))){ 	//septima rama
				//printf("\n");
				for(j=0; j<(n-6); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t7.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-7)){
							//printf("\n");
							for(k=0; k<(n-6); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t7.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-7))){ 	//octave rama
				//printf("\n");
				for(j=0; j<(n-7); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t8.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-8)){
							//printf("\n");
							for(k=0; k<(n-7); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t8.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i == (TAM-(n-8))){ 	//novena rama
				//printf("\n");
				for(j=0; j<(n-8); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t9.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);
						if(j==(n-9)){
							//printf("\n");
							for(k=0; k<(n-8); k++){
								if((id_proc3=fork())==0){								
									pid = getpid();
									ppid = getppid();
									printf("\n\t\t9.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
									exit(0);
								}
								wait(&id_proc3);
							}
						}
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}
			else if(i== (TAM-(n-9))){	//ultima rama
				for(j=0; j<(n-9); j++){
					if((id_proc2=fork())==0){
						pid = getpid();
						ppid = getppid();
						printf("\n\t10.-Soy el proceso hijo numero: %d Mi padre es:%d\n",pid, ppid);	
						if((id_proc3=fork())==0){
							for(k=0; k<(n-9); k++){
								pid = getpid();
								ppid = getppid();
								printf("\n\t\t10.-Soy el proceso hijo terminal numero: %d Mi padre es: %d\n",pid, ppid);	
								exit(0);
							}
						}
						wait(&id_proc3);
					}
					else{
						wait(&id_proc2);
						break;					
					}
				}
			}			
			exit(0);
		}
	}
	exit(0);
	//return 0;
}