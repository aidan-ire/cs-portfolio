CC = cc
CFLAGS = -Wall
PROGS = socketserver socketclient

all: $(PROGS)

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(PROGS) *.class

.PHONY: all clean
