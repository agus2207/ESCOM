public class algoritmos {
    
    public algoritmos(){
    }
    
    public int MCD(int a,int b){
        int cociente,residuo;
        residuo=a%b;
        cociente=a/b;
        if(residuo>0)
          return MCD(b,residuo);
        else
            return b;
        
    }
    public int EXP(int a, int b, int m){
    System.out.println("Valor de b=:"+b);
    if(b==0)
        return 1;
    int tmp=EXP(a,b/2,m);
    if(b%2==0){
        System.out.println("Valor tmp%m="+tmp%m+"Valor de par:"+(tmp%m)*(tmp%m));
        return (((tmp%m)*(tmp%m))%m);
    }
    else{
        System.out.println("Valor tmp%m="+tmp%m+"Valor de impar:"+(tmp%m)*(tmp%m)*(a%m));
        return (((tmp%m)*(tmp%m)*(a%m))%m);
    }
    }
    
}
