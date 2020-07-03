disp('ADALINE');

tam_p = 2;%input('Ingrese el numero de elementos en p\n');
clases = 4;%input('Ingrese el numero de datos del dataset\n');
tam_target = 1;%input('Ingrese el numero de elementos del target\n');

Eepoch = 0.01;%input('Ingrese el valor de Eepoch\n');
alpha = 0.2;%input('Ingrese el valor de alpha\n');
epoch_max = 5;%input('Ingrese el valor de epochmax\n');
problema = 1;%input('Seleccione el numero de problema a resolver\n1.Calsificacion\n2.Regresion\n');

archivo_entrenamiento = 'Entrenamiento.txt';%input('Ingrese el nombre del archivo con el conjunto a entranar\n', 's');

fid=fopen(archivo_entrenamiento,'r');
formatSpec = '%d';
sizeA = [tam_p+tam_target clases];
datos_archivo = fscanf(fid,formatSpec,sizeA)
fclose(fid);

datos_entrenamiento = zeros(tam_p, clases);
datos_target = zeros(tam_target, clases);
k = 1;

for i = 1:tam_p+tam_target
    for j = 1:clases
        if(i <= tam_p)
            datos_entrenamiento(i,j) = datos_archivo(i,j);
        else
            datos_target(k, j) = datos_archivo(i,j);
        end
        if(i > tam_p && j == clases)
            k = k+1;
        end
    end
end

datos_entrenamiento
datos_target

bias = (1-(-1)).*rand(tam_target, 1)-1
pesos = (1-(-1)).*rand(tam_target, tam_p)-1
p = zeros(tam_p, 1);
t = zeros(tam_target, 1);
error = zeros(1,tam_target);
suma_eepoch = 0;
epoca = 0;
grafica = bias';

for i = 1:tam_target
    x = pesos(i,:);
    grafica = [grafica,x];
end

Eepoch_evolucion = Eepoch;

for i = 1:(tam_target-1)
    Eepoch_evolucion = [Eepoch_evolucion,Eepoch];
end

for i = 1:epoch_max 
    for j = 1:clases 
        for k = 1:tam_p
           p(k,1) = datos_entrenamiento(k,j);
           if(k <= tam_target)
               t(k,1) = datos_target(k,j);
           end
        end
        if(problema == 1)
            a = purelin((pesos*p)+bias)
            e = t-a
            suma_eepoch = suma_eepoch + e;
        elseif (problema == 2)
            a = purelin((pesos*p))
            e = t-a
            suma_eepoch = suma_eepoch + e;
        end
       
        if(any(e ~= 0))
            pesos = pesos + (2*alpha*e*p')
            bias = bias + (2*alpha*e)
            temp = bias';
            for l = 1:tam_target
                x = pesos(l,:);
                temp = [temp,x];
            end
            grafica = [grafica;temp];
        else
            temp = bias';
            for l = 1:tam_target
                x = pesos(l,:);
                temp = [temp,x];
            end
            grafica = [grafica;temp];
        end
        error = [error;e'];
    end
    
    res_eepoch = (1/clases)*suma_eepoch
    
    for j = 1:clases 
         Eepoch_evolucion = [Eepoch_evolucion;res_eepoch'];
    end
    
    if(all(error == 0))
        epoca = i;
        break
    elseif((res_eepoch < Eepoch))
        epoca = i;
        break
    else
        error = zeros(1,tam_target);
        suma_eepoch = 0;
    end
end


%grafica = [grafica,Eepoch_evolucion];

if(epoca ~= 0) 
    respuesta = "Entrenamiento correcto en la EPOCA " +epoca
    
    fid = fopen('val_finales.txt','w');
    fprintf(fid,'%s\r\n','PESOS');
    for i = 1:tam_target
        for j = 1:tam_p
            fprintf(fid,'%.4f\r\n',pesos(i,j));
        end
    end
    fprintf(fid,'%s\r\n','BIAS');
    for i = 1:tam_target
        fprintf(fid,'%.4f\r\n',bias(i,1));
    end
    fclose(fid);
    
    h = plot(grafica, '-o','LineWidth',3);
    [m,n] = size(grafica);
    etiquetas = "";
    bandera = 1;
    for i = 1:n
        if(i <= tam_target)
            etiquetas = etiquetas +"bias"+ i+" ";
        else
            if(i < n)
                etiquetas = etiquetas + "W"+bandera+" ";
                bandera = bandera+1;
            else
                etiquetas = etiquetas + "W"+bandera;
            end
        end
    end
    
    set(h,{'DisplayName'}, cellstr(strsplit(etiquetas)'))
    hold on
    h = plot(Eepoch_evolucion, '-o','LineWidth',3);
    set(h,{'DisplayName'}, {'EEPOCH'})
    legend show
    
    if(problema == 1)
        figure(2)
        for i = 1:clases
            x = datos_entrenamiento(1,i);
            if(tam_p == 2)
                y = datos_entrenamiento(2,i);
            elseif(tam_p == 3)
                y = datos_entrenamiento(2,i);
                z = datos_entrenamiento(3,i);
            end
            plot(x,y,'*','LineWidth',1)
            hold on
        end

        pesos
        bias
        for i = 1:tam_target
            temp = pesos(i,:);
            w1 = temp(1,1);
            w2 = temp(1,2);
            for j = 1:tam_target
                if(j == i)
                    b = bias(j,1);
                end
            end
            p1 = -(b)/w1;
            p2 = -(b)/w2;
            ejex = [p1,0];
            ejey = [0,p2];
            m = -p2/p1;
            %bn = p2-(m*p1);
            x = [-4,4];
            y = (m*x)+p2;
            grid on
            plot(x,y,'LineWidth',1)
        end
    end
    
else
    disp('Entrenamiento incorrecto')
end