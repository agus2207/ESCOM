disp('Perceptron')
%El formato para las matrices es [filas columnas]

tam_p = 2;%input('Ingrese el numero de elementos en p\n');
clases = 4;%input('Ingrese el numero de datos del dataset\n');
tam_target = 1;%input('Ingrese el numero de elementos del target\n');
max_epoch = 30;%input('Ingrese el numero de max_epoch\n');

archivo_entrenamiento = 'Entrenamiento.txt';%input('Ingrese el nombre del archivo con el conjunto a entranar\n', 's');
%archivo_target = input('Ingrese el nombre del archivo con los targets\n', 's');

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

bias = (1).*randn(tam_target, 1)
pesos = (1).*randn(tam_target, tam_p)
clase = zeros(tam_p, 1);
target = zeros(tam_target, 1);
error = zeros(1,tam_target);
epoca = 0;
grafica = bias';

for i = 1:tam_target
    x = pesos(i,:);
    grafica = [grafica,x];
end

for i = 1:max_epoch
    for j = 1:clases
        for k = 1:tam_p
           clase(k,1) = datos_entrenamiento(k,j);
           if(k <= tam_target)
               target(k,1) = datos_target(k,j);
           end
        end
        
        a = hardlim((pesos*clase)+bias)
        e = target - a
        
        if(any(e ~= 0))
            pesos = pesos + (e*clase')
            bias = bias + e
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
    if(all(error == 0))
        epoca = i;
        break
    else
        error = zeros(1,tam_target);
    end
end

if(epoca ~= 0)
    respuesta = "Entrenamiento correcto en la EPOCA " +epoca
    
    fid = fopen('val_finales.txt','w');
    fprintf(fid,'%s\r\n','PESOS');
    for i = 1:tam_target
        for j = 1:tam_p
            fprintf(fid,'%i\r\n',pesos(i,j));
        end
    end
    fprintf(fid,'%s\r\n','BIAS');
    for i = 1:tam_target
        fprintf(fid,'%i\r\n',bias(i,1));
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
    legend show
    
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
        x = [-8,8];
        y = (m*x)+p2;
        grid on
        plot(x,y,'LineWidth',1)
        vectorx = [0,w1];
        vectory = [0,w2];
        plot(vectorx,vectory,'LineWidth',3)
    end
    
else
    disp('Entrenamiento incorrecto')
end


