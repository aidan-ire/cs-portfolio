CC = cc
CFLAGS = -Wall
PROGS = p4a p4b p4c p4d p4e p4f p4g p4h p4problem

all: $(PROGS)

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(PROGS)

.PHONY: all clean
