from collections import deque
c = deque([])
a = int(input("Please choose (1)enqueue (2)dequeue (0)end:"))
while a != 0:
	if a == 1:
		b = input("Please input data:")
		c.append(b)
		print(c)
	elif a ==2:
		count = len(c)
		if count == 0:
			print("empty")
		else:
			b = input("Please input data:")
			c.remove(b)
			print(c)
	else:
		print("Error input")
	a = int(input("Please choose (1)enqueue (2)dequeue (0)end:"))