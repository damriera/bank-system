import random
import time


t = time.time()
name = "dummy"
tab = []

for i in range(1000):
    name1 = name + str(i)
    tab.append([str(name1), str(i), str(random.uniform(-15000, 15000))])


print("array generated in ", time.time()-t)


t = time.time()


with open("data/testFile.txt", "w") as file:
    for person in tab:
        line = " : ".join(person)
        line += "\n"
        file.write(line)
        if int(person[1])%100 == 0:
            print(f"wrote line {person[1]}")
        

print("data wrote in ", time.time() - t)