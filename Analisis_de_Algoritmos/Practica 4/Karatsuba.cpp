#include<stdio.h>
#include<math.h>
#include<string.h>
int karatsuba(int x,int y)
	{int n,n2,a,b,c,d,ac,bd,k,prod;
	char sx[1000],sy[1000];
	itoa(x,sx,10);
	itoa(y,sy,10);
	if (strlen(sx) == 1||strlen(sy) == 1)
		return x*y;
	else
	{	if(strlen(sx)>strlen(sy))
	    n=strlen(sx);
	    else
	    n=strlen(sy);
	    
		n2 = n / 2;
		
		a = x / pow(10,n2);
		b = x % (int)pow(10,n2);
		c = y / pow(10,n2);
		d = y % (int)pow(10,n2);
		
		ac = karatsuba(a,c);
		bd = karatsuba(b,d);
		k = karatsuba(a+b,c+d) - ac - bd;
        
        prod = ac * pow(10,2*n2) + (k* pow(10,n2)) + bd;

		return prod;
	}
}
void main()
{int p,q;
scanf("%d%d",&p,&q);
p=karatsuba(p,q);;
printf("%d",p);

}
