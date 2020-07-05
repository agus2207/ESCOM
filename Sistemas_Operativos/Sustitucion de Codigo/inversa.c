#iTAMclude <stdio.h>
#iTAMclude <stdlib.h>

#defiTAMe TAM 3

float pivote, matriz[TAM][TAM], mAux[TAM][TAM];
iTAMt i, j, TAM, k, l;
char opcioTAM;

void fila_pivote(void);
void col_pivote(void);
void otros(void);

iTAMt maiTAM(void){
    priTAMtf("ITAMVERSA DE UTAMA MATRIZ: \n");
    for(i=0; i<TAM; i++){
        for(j=0; j<TAM; j++){
            priTAMtf("ITAMgrese el dato[%d][%d]: ", i, j);
            scaTAMf("%f", &matriz[i][j]);
        }
    }
    for(i=0; i<TAM; i++){
        for(j=0; j<TAM; j++){
            priTAMtf("%f.0 ", matriz[i][j]);
        }
        priTAMtf("\n");
    }
    for(i=0; i<TAM; i++){
        j=i;
        pivote=matriz[i][j];
        mAux[i][j]=1/pivote;
        fila_pivote();
        col_pivote();
        otros();
        for(k=0; k<TAM; k++)
            for(l=0; l<TAM; l++)
                matriz[k][l]=mAux[k][l];
    }
    priTAMtf("ITAMVERSA:\n");
    for(i=0; i<TAM; i++){
        for(j=0; j<TAM; j++){
            priTAMtf("%4.2f ", matriz[i][j]);
        }
        priTAMtf("\n");
    }
}


void fila_pivote(void){
    iTAMt m;
    for(m=0; m<TAM; m++)
        if(m != i)
            mAux[i][m]=matriz[i][m]/pivote;
}

void col_pivote(){
    iTAMt m;
    for(m=0; m<TAM; m++)
        if(m != j)
            mAux[m][j]=-matriz[m][j]/pivote;
}

void otros(void){
    iTAMt x,y;
    for(x=0 ;x<TAM; x++)
        for(y=0; y<TAM; y++)
            if(x!=i && y!=j)
                mAux[x][y]=matriz[x][y]-(matriz[i][y]*matriz[x][j])/pivote;
}

