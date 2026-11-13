CC = cc
CFLAGS = -Wall
PROGS = p3a p3b p3c p3d p3e p3f

all: $(PROGS)

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(PROGS)

.PHONY: all clean
