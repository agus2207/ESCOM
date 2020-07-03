 prompt = 'Elige una opcion:\n 1)NOT\n 2)AND\n 3)OR\n';
 x = input(prompt);
 if(x == 1)
     disp('Escogiste NOT')
     dataset = crear_dataset(1, 1);
     pesos = generar_numeros(1);
     prompt = 'Introduce numero de entrenamientos entre (50-200):\n';
     epocas = input(prompt);
     entrenamiento(dataset, pesos, epocas, 1, x);
 elseif(x == 2)
        disp('Escogiste AND')
        prompt = 'Introduce la dimensión de la compuerta:\n';
        tam = input(prompt);
        dataset = crear_dataset(tam, 2);
        pesos = generar_numeros(tam);
        prompt = 'Introduce numero de entrenamientos entre (50-200):\n';
        epocas = input(prompt);
        entrenamiento(dataset, pesos, epocas, tam, x);
 elseif(x == 3)
        prompt = 'Introduce la dimensión de la compuerta:\n';
        tam = input(prompt);
        dataset = crear_dataset(tam, 3)
        pesos = generar_numeros(tam);
        prompt = 'Introduce numero de entrenamientos entre (50-200):\n';
        epocas = input(prompt);
        entrenamiento(dataset, pesos, epocas, tam, x);
 else
     disp('Opcion invalida')
 end
 
 
 function y = crear_dataset(tam, compuerta)
     d = (0:2^tam-1);
     b = de2bi(d, 'left-msb');
     if(compuerta == 1)
         x = [1;0];
         y = [b,x];
     elseif(compuerta == 2)
         x = ones(2^tam,1);
         for i = 1:2^tam
             for j = 1:tam
                 x(i,1) = x(i,1)&b(i,j);
             end
         end
         y = [b, x];
     else
         x = zeros(2^tam,1);
         for i = 1:2^tam
             for j = 1:tam
                 x(i,1) = x(i,1)|b(i,j);
             end
         end
         y = [b, x];
     end
 end
 
 function z = generar_numeros(tam)
      z = randi([-100000,100000],1,tam);
 end
 
 function w = entrenamiento(dataset, pesos, epocas, tam, compuerta)
    theta = randi([-100000,100000],1,1)
    n = 0;
    resultado = zeros(1, 2^tam);
    w = 1;
    for i = 1:epocas
        for j = 1:2^tam
            for k = 1:tam
                n = (dataset(j,k)*pesos(1,k))+n;
            end
            if(n > theta)
                a = 1;
                dataset(j,tam+1);
            else
                a = 0;
                dataset(j,tam+1);
            end
            n = 0;
            if(a == dataset(j,tam+1))
                resultado(1, j) = 1;
            else
                resultado(1, j) = 0;
                pesos = generar_numeros(tam);
                theta = randi([-100000,100000],1,1);
            end
        end
    end
    for j = 1:2^tam
         w = w&resultado(1,j);
    end
    if(w == 1)
        disp('Entrenamiento correcto')
        fid=fopen('valores.txt','a');
        if(compuerta == 1)
            fprintf(fid,'s\r\n','COMPUERTA NOT');
            fprintf(fid,'s','TAMAÑO: ');
            fprintf(fid,'d\r\n\r\n',tam);
        elseif(compuerta == 2)
            fprintf(fid,'s\r\n','COMPUERTA AND');
            fprintf(fid,'s','TAMAÑO: ');
            fprintf(fid,'d\r\n\r\n',tam);
        else
            fprintf(fid,'s\r\n','COMPUERTA OR');
            fprintf(fid,'s','TAMAÑO: ');
            fprintf(fid,'d\r\n\r\n',tam);
        end
        fprintf(fid,'s\r\n','NUMERO DE EPOCAS:');
        fprintf(fid,'d\r\n\r\n',epocas);
        fprintf(fid,'s\r\n','VALORES DE W:');
        for i = 1:tam
            fprintf(fid,'i\r\n',pesos(1,i));
        end
        fprintf(fid,'s\r\n','');
        fprintf(fid,'s\r\n','VALOR DE THETA:');
        fprintf(fid,'i\r\n\r\n',theta(1,1));
        fclose(fid);
    else
        disp('Entrenamiento fallido') 
        while(w == 0)
            entrenamiento(dataset, pesos, epocas, tam, compuerta);
        end
        prompt = 'Intente de nuevo\n 1)Si\n 2)No\n';
        opcion = input(prompt);
        if(opcion == 1)
            entrenamiento(dataset, pesos, epocas, tam, compuerta);
        else 
            disp('Hasta pronto')
        end
     end
 end

 %a = rand(5,5);
 %dlmwrite('myFile.txt',a,'delimiter',' ','precision','.4f')
 %dlmwrite('filename.txt','Delimiter','t',a)