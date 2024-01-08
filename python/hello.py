# print("Hello World!")
def hello(parametro = "mundo"):
    print("olá, " + parametro)



hello()

nome = input("Qual seu nome?\n") # input -> pega uma string e retorna uma string (texto) (input = entrada)
hello(nome)

"""
nome = nome.strip().title() # == .trim() do Java
# nome = nome.capitalize() # -> so a primeira
# nome = nome.title() # -> todas as primeiras letras

prim, sobre = nome.split(" ")

print("Olá, " + nome + "!") # junta as strings
print(f"Espero que esteja aprendendo bem {prim}")
# print("Olá, ", end="") # não pula linha
# print(f"Olá, {nome}") # o f antes da string permite usar variáveis dentro da string

"""