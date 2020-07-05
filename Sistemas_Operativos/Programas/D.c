#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
int main (void){
	int id_proc
 	printf("ARBOL DE PROCESOS DEL INCISO "D" ");
	for(int i=0; i<=2; i++){
		if((id_proc=fork())==0){
			printf("Soy el proceso hijo")
			exit(0);
			if(i==0){
				for(int j=0; j<=2; j++){
					if((id_proc=fork())==0){
						printf("Soy el hijo 1 del proceso 1");
						exit(0);
						if(j==1){
							for(int k=0; k<=3; k++){
								if((id_proc=fork())==0){
									printf("Soy el hijo 1 del proceso 1,1");
									exit(0);
									if(k==1){
										for(int l=0; l<=3; l++){
											if((id_proc=fork())==0){
												printf("Soy el hijo 1 del proceso 1,1,1");
												if(l==3){
													for(int m=0; m<=2; m++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
									else if(k==2){
										for(int n=0;n<=2; n++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(n==2){
													for(int o=0; o<=3; o++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
									else if(k==3){
										for(int p=0; p<=1; p++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(p==1){
													for(int q=0; q<=4; q++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
								}
								else{
									printf("Soy el proceso padre");
								}
							}
						}
						else if(j==2){
							for(int r=0; r<=2; r++){
								if((id_proc=fork())==0){
									printf("Soy el proceso hijo");
									exit(0);
								}
								else{
									printf("Soy el proceso padre");
								}
							}
						}
					}
					else{
						printf("Soy el proceso padre");
					}
				}
			}
			else if(i==2){
				for(int s=0; s<=3; s++){
					if((id_proc=fork())==0){
						printf("Soy el proceso hijo");
						exit(0);
						if(s==1){
							for(int t=0; t<=2; t++){
								if((id_proc=fork())==0){
									printf("Soy el proceso hijo");
									exit(0);
								}
								else{
									printf("Soy el proceso padre");
								}
							}
						}
						else if(s==2){
							for(int u=0; u<=3; u++){
								if((id_proc=fork())==0){
									printf("Soy el proceso hijo");
									exit(0);
									if(u==1){
										for(int v=0; v<=3; v++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(v==3){
													for(int w=0; w<=4; w++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
									else if(u==2){
										for(int x=0; x<=2; x++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(x==2){
													for(int y=0; y<=3; y++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
									else if(u==3){
										for(int z=0; z<=1; z++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(z==1){
													for(int a=0; a<=2; a++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
								}
								else{
									printf("Soy el proceso padre");
								}
							}
						}
						else if(s==3){
							for(int b=0; b<=4; b++){
								if((id_proc=fork())==0){
									printf("Soy el proceso hijo");
									exit(0);
									if(b==3){
										for(int c=0; c<=2; c++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(c==2){
													for(int d=0; d<=3; d++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
															if(d==1){
																for(int e=0; e<=2; e++){
																	if((id_proc=fork())==0){
																		printf("Soy el proceso hijo");
																		exit(0);
																	}
																	else{
																		printf("Soy el proceso padre");
																	}
																}
															}
															else if(d==3){
																for(int f=0; f<=2; f++){
																	if((id_proc=fork())==0){
																		printf("Soy el proceso hijo");
																		exit(0);
																	}
																	else{
																		printf("Soy el proceso padre");
																	}
																}
															}
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
									else if(b==4){
										for(int g=0; g<=1; g++){
											if((id_proc=fork())==0){
												printf("Soy el proceso hijo");
												if(g==1){
													for(int h=0; h<=2; h++){
														if((id_proc=fork())==0){
															printf("Soy el proceso hijo");
															exit(0);
															if(h==2){
																for(int i1=0; i1<=1; i1++){
																	if((id_proc=fork())==0){
																		printf("Soy el proceso hijo");
																		if(i1==1){
																			for(int j1=0; j1<=3; j1++){
																				if((id_proc=fork())==0){
																					printf("Soy el proceso hijo");
																					exit(0);
																				}
																				else{
																					printf("Soy el prosceso padre");
																				}
																			}
																		}
																	}
																	else{
																		printf("Soy el proceso padre");
																		break;
																	}
																}
															}
														}
														else{
															printf("Soy el proceso padre");
														}
													}
												}
											}
											else{
												printf("Soy el proceso padre");
												break;
											}
										}
									}
								}
								else{
									printf("Soy el proceso padre");
								}
							}
						}
					}
					else{
						printf("Soy el proceso padre");
					}
				}
			}
		}
		else{
			printf("Soy el proceso padre");
		}
	}
}