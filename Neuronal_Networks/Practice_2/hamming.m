disp('Red de Hamming')
disp('La siguiente red consta de 5 clases y 3 sensores')
disp('Clase 1: Avenger')
disp('Clase 2: X-men')
disp('Clase 3: Inhuman')
disp('Clase 4: Defender')
disp('Clase 5: Runaway')
s = 5;
r = 3;
archivo_pesos = input('Ingrese el nombre del archivo de pesos\n', 's');
fid=fopen(archivo_pesos,'r');
formatSpec = '%d';
sizeA = [r s];
pesos = fscanf(fid,formatSpec,sizeA);
pesos = pesos'
fclose(fid);
archivo_entrada = input('Ingrese el nombre del archivo del vector de entrada\n', 's');
fid = fopen(archivo_entrada,'r');
formatSpec = '%d';
sizeA = [r 1];
p = fscanf(fid,formatSpec,sizeA)
fclose(fid);
bias = zeros(s,1);
for i = 1:s
    bias(i,1) = r;
end
respuesta = pesos * p;
salida = respuesta + bias
limite = 1/(s-1);
epsilon = (limite).*rand(1)
diagonal = ones(s,1);
capa_r = diag(diagonal);
for i = 1:s
    for j = 1:s
        if(capa_r(i,j) == 0)
            capa_r(i,j) = -epsilon;
        end
    end
end
answer = zeros(s,1);
datos = zeros(100,s);
for i = 1:100
    final = capa_r*salida;
    salida = poslin(final);
    answer = poslin(final);
    for j = 1:s
        datos(i,j) = answer(j,1);
    end
end
answer
clase = ones(1,s);
for i = 1:s
    if(answer(i,1) == 0)
        clase(1,i) = 0;
    end
end
cantidad=numel(clase(clase==1));
if(cantidad == 1)
    for i = 1: s
        if(clase(1,i) == 1)
            if(i == 1)
                disp('Pertenece a la clase Avenger')
            elseif(i == 2)
                disp('Pertenece a la clase X-men')
            elseif(i == 3)
                disp('Pertenece a la clase Inhuman')
            elseif(i == 4)
                disp('Pertenece a la clase Defender')
            elseif(i == 5)
                disp('Pertenece a la clase Runaway')
            end
        end
    end
else
    disp('No se pudo clasificar')
end
h = plot(datos, '--','LineWidth',3);
set(h,{'DisplayName'},{'Avenger';'X-Men';'Inhuman';'Defender';'Runaway'})
legend show