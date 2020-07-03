disp('Perceptron Multicapa');

tam = 701;%input('Ingrese la cantidad de datos a entrenar:\n');
datos_entrada = 'datos_entrada.txt';%input('Ingrese el nombre del archivo con los datos de entrada:\n', 's');
datos_target = 'datos_target.txt';%input('Ingrese el nombre del archivo con los valores deseados:\n', 's');
rango = [-2 2];%input('Ingrese el rango de la señal:\n');
disp('Separacion de datos');
disp('1.) 70% train-15% validacion-15%test');
disp('2.) 80%train-10%validacion-10%test');
opcion_separacion = 2;%input('Seleccione una opcion de separacion para sus datos:\n');
disp('Arquitectura');
v1 = [1 10 7 1];%input('Ingrese valores del vector 1:\n');
v2 = [3 3 1];%input('Ingrese valores del vector 2:\n');
alpha = 0.001;%input('Ingrese el valor de alpha:\n');
epoch_max = 100;%input('Ingrese el valor de epoch max:\n');
eepoch = 0.00001;%input('Ingrese el valor minimo de aprendizaje:\n');
epoch_val = 10;%input('Ingrese el porcentaje de epocas de validacion:\n');
numval = 4;%input('Ingrese el numero de incremento consecutivo para el error de validacion:\n');

fid=fopen(datos_entrada,'r');
formatSpec = '%f';
sizeA = [tam, 1];
datos_p = fscanf(fid,formatSpec,sizeA);
fclose(fid);

fid=fopen(datos_target,'r');
formatSpec = '%f';
sizeA = [tam, 1];
datos_t = fscanf(fid,formatSpec,sizeA);
fclose(fid);

epoca_val = floor(epoch_val*epoch_max/100);
entrenamiento = 0;
validacion = 0;
test = 0;

if(opcion_separacion == 1)
    entrenamiento = floor(tam*70/100);
    validacion = floor(tam*15/100);
    test = floor(tam*15/100);
elseif(opcion_separacion == 2)
    entrenamiento = floor(tam*80/100);
    validacion = floor(tam*10/100);
    test = floor(tam*10/100);
end

residuo = tam - (entrenamiento + validacion + test);
entrenamiento = entrenamiento + residuo;

entrenamiento_p = zeros(entrenamiento, 1);
validacion_p = zeros(validacion, 1);
test_p = zeros(test, 1);
entrenamiento_t = zeros(entrenamiento, 1);
validacion_t = zeros(validacion, 1);
test_t = zeros(test, 1);
contador = 1;
contador_tv = 1;
contador_entrenamiento = 1;
contador_separador = floor(entrenamiento/validacion)+2;

for i = 1:tam
    if(contador == 1 && i == tam)
        entrenamiento_p(contador_entrenamiento, 1) = datos_p(i,1);
        entrenamiento_t(contador_entrenamiento, 1) = datos_t(i,1);
    elseif(contador == 1)
        test_p(contador_tv,1) = datos_p(i,1);
        test_t(contador_tv,1) = datos_t(i,1);
        contador = contador+1;
    elseif(contador == contador_separador)
        validacion_p(contador_tv,1) = datos_p(i,1);
        validacion_t(contador_tv,1) = datos_t(i,1);
        contador = 1;
        contador_tv = contador_tv+1;
    else
        entrenamiento_p(contador_entrenamiento, 1) = datos_p(i,1);
        entrenamiento_t(contador_entrenamiento, 1) = datos_t(i,1);
        contador = contador+1;
        contador_entrenamiento = contador_entrenamiento+1;
    end
end

contador_numval = 0;
pesos = cell(size(v2,2),1);
bias = cell(size(v2,2),1);

pesos_graficas = cell(size(v2,2),1);
bias_graficas = cell(size(v2,2),1);

a = cell(size(v2,2),1);
sensitividad = cell(size(v2,2),1);

for i = 2:size(v1,2)
    pesos_temp = 2.*rand(v1(1,i), v1(1,i-1))-1;
    bias_temp = 2.*rand(v1(1,i), 1)-1;
    pesos{i-1, 1} = pesos_temp;
    nombre1 = "W"+(i-1)+".txt";
    nombre2 = "bias"+(i-1)+".txt";
    dlmwrite(nombre1,pesos{i-1, 1},'delimiter',' ','precision','%.4f');
    bias{i-1,1} = bias_temp;
    dlmwrite(nombre2,bias{i-1, 1},'delimiter',' ','precision','%.4f');
    pesos_graficas{i-1, 1} = pesos_temp;
    bias_graficas{i-1, 1} = bias_temp;
end

error_entrenamiento = 0;
error_validacion = cell(2,1);
error_validacion{1,1} = 0;
error_validacion{2,1} = 0;
error_test = 0;

error_entrenamiento_matriz = [0 0];
error_validacion_matriz = [0 0];

for i = 1:epoch_max 
    if(mod(i,epoca_val) == 0)
        'validacion'
        for j = 1:size(validacion_p,1)
            %a{1,1} = validacion_p(j,1);
            for k = 1:size(v2,2)
                if(v2(1,k) == 1)
                    if(k == 1)
                        a{k,1} = purelin((pesos{k,1}*validacion_p(j,1))+bias{k,1});
                    else
                        a{k,1} = purelin((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                elseif(v2(1,k) == 2)
                    if(k == 1)
                        a{k,1} = logsig((pesos{k,1}*validacion_p(j,1))+bias{k,1});
                    else
                        a{k,1} = logsig((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                elseif(v2(1,k) == 3)
                    if(k == 1)
                        a{k,1} = tansig((pesos{k,1}*validacion_p(j,1))+bias{k,1});
                    else
                        a{k,1} = tansig((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                end
            end
            
            error = validacion_t(j,1) - a{size(v2,2),1};
            error_validacion{1,1} = error_validacion{1,1} + error;
            
        end
        error_validacion{1,1} = abs((1/size(validacion_p,1))*error_validacion{1,1});
        error_validacion_matriz = [error_validacion_matriz;[i error_validacion{1,1}]];
        
        if(error_validacion{1,1} > error_validacion{2,1})
            contador_numval = contador_numval + 1;
            error_validacion{2,1} = error_validacion{1,1};
            error_validacion{1,1} = 0;
        else
            contador_numval = 0;
            error_validacion{2,1} = error_validacion{1,1};
            error_validacion{1,1} = 0;
        end
        if(contador_numval == numval)
            "Rompo"
            break;
        end
        
    else
        for j = 1:size(entrenamiento_p,1)
            %a{1,1} = entrenamiento_p(j,1);
            for k = 1:size(v2,2)
                if(v2(1,k) == 1)
                    if(k == 1)
                        a{k,1} = purelin((pesos{k,1}*entrenamiento_p(j,1))+bias{k,1});
                    else
                        a{k,1} = purelin((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                elseif(v2(1,k) == 2)
                    if(k == 1)
                        a{k,1} = logsig((pesos{k,1}*entrenamiento_p(j,1))+bias{k,1});
                    else
                        a{k,1} = logsig((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                elseif(v2(1,k) == 3)
                    if(k == 1)
                        a{k,1} = tansig((pesos{k,1}*entrenamiento_p(j,1))+bias{k,1});
                    else
                        a{k,1} = tansig((pesos{k,1}*a{k-1,1})+bias{k,1});
                    end
                end
            end
            
            error = entrenamiento_t(j,1) - a{size(v2,2),1};
            error_entrenamiento = error_entrenamiento + error; 
            
            for k = size(v2,2):-1:1
                if(k == size(v2,2))
                    if(v2(1,k) == 1)
                        f = 1;
                        sensitividad{k,1} = -2*f*error;
                    elseif(v2(1,k) == 2)
                        f = diag(diag(ones(size(a{k,1},1),1)-a{k,1})*a{k,1});
                        sensitividad{k,1} = -2*f*error;
                    elseif(v2(1,k) == 3)
                        f = diag(ones(size(a{k,1},1)-(a{k,1}.^2)));
                        sensitividad{k,1} = -2*f*error;
                    end
                else
                    if(v2(1,k) == 1)
                        f = 1;
                        sensitividad{k,1} = f*pesos{k+1,1}'*sensitividad{k+1,1};
                    elseif(v2(1,k) == 2)
                        f = diag(diag(ones(size(a{k,1},1),1)-a{k,1})*a{k,1});
                        sensitividad{k,1} = f*pesos{k+1,1}'*sensitividad{k+1,1};
                    elseif(v2(1,k) == 3)
                        f = diag(ones(size(a{k,1},1),1)-(a{k,1}.^2));
                        sensitividad{k,1} = f*(pesos{k+1,1}'*sensitividad{k+1,1});
                    end
                end
            end
            for k = size(v2,2):-1:1
                if(k == 1)
                    pesos_temp = pesos{k, 1}-(alpha*sensitividad{k,1}*entrenamiento_p(j,1)');
                    bias_temp = bias{k,1}-(alpha*sensitividad{k,1});
                else
                    pesos_temp = pesos{k, 1}-(alpha*sensitividad{k,1}*a{k-1,1}');
                    bias_temp = bias{k,1}-(alpha*sensitividad{k,1});
                end
                pesos{k, 1} = pesos_temp;
                bias{k,1} = bias_temp;
                nom_temp1 = "W"+k+".txt";
                nom_temp2 = "bias"+k+".txt";
                dlmwrite(nom_temp1,pesos{k, 1},'-append','delimiter',' ','precision','%.4f','roffset',1);
                dlmwrite(nom_temp2,bias{k, 1},'-append','delimiter',' ','precision','%.4f','roffset',1);
                pesos_graficas{k,1} = [pesos_graficas{k,1};pesos_temp];
                bias_graficas{k,1} = [bias_graficas{k,1};bias_temp];
            end
        end
        error_promedio = abs((1/size(entrenamiento_p,1))*error_entrenamiento);
        error_entrenamiento_matriz = [error_entrenamiento_matriz;[i error_promedio]];
        error_entrenamiento = 0;
        if(error_promedio < eepoch)
            error_promedio
            epoca = i;
            "Exito "+i
            break;
        else
            error_entrenamiento = 0;
        end
    end
end

grafica_test = zeros(size(test_p,1),1);
for i = 1:size(test_p,1)
    %a{1,1} = test_p(i,1);
    for k = 1:size(v2,2)
        if(v2(1,k) == 1)
            if(k == 1)
                a{k,1} = purelin((pesos{k,1}*test_p(i,1))+bias{k,1});
            else
                a{k,1} = purelin((pesos{k,1}*a{k-1,1})+bias{k,1});
            end
        elseif(v2(1,k) == 2)
            if(k == 1)
                a{k,1} = logsig((pesos{k,1}*test_p(i,1))+bias{k,1});
            else
                a{k,1} = logsig((pesos{k,1}*a{k-1,1})+bias{k,1});
            end
        elseif(v2(1,k) == 3)
            if(k == 1)
                a{k,1} = tansig((pesos{k,1}*test_p(i,1))+bias{k,1});
            else
                a{k,1} = tansig((pesos{k,1}*a{k-1,1})+bias{k,1});
            end
        end
    end
    grafica_test(i,1) = a{size(v2,2),1};      
    error = test_t(i,1) - a{size(v2,2),1};
    error_test = error_test + error; 
end

error_promedio = abs((1/size(test_p,1))*error_test)

fid = fopen('val_finales.txt','w');
for i = 1:size(v2,2)
    nom_temp1 = "W"+i;
    nom_temp2 = "bias"+i;
    %dlmwrite('val_finales.txt',nom_temp1,'-append','delimiter',' ','precision','%.4f','roffset',1);
    dlmwrite('val_finales.txt',pesos{i,1},'-append','delimiter',' ','precision','%.4f','roffset',1);
    %dlmwrite('val_finales.txt',nom_temp2,'-append','delimiter',' ','precision','%.4f','roffset',1);
    dlmwrite('val_finales.txt',bias{i,1},'-append','delimiter',' ','precision','%.4f','roffset',1);
end
fclose(fid);

figure(1)
plot(test_p,test_t, 'o')
hold on
plot(test_p,grafica_test, 'x')

figure(2)
plot(error_entrenamiento_matriz(:,1),error_entrenamiento_matriz(:,2), 'o')
hold on
plot(error_validacion_matriz(:,1),error_validacion_matriz(:,2), 'o')

for i=1:size(v2,2)
    figure
    plot(pesos_graficas{i,1})
    hold on
    plot(bias_graficas{i,1})
end