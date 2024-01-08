def main():
    x = float(input("Digite um número: "))
    print("o número quadrado é ", square(x))
    
    
def square(x):
    return pow(x, 2)



main()

"""
y = float(input("Digite outro número: "))
    z  = round(x + y)
    a = round(x / y,2)
    # print(f"{z:,}") -> formata
    print("soma: "+ str(z) + " divisão: " + str(a))
    
"""