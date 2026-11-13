CC = cc
CFLAGS = -Wall
PROGS = namedpipe simpleoutput outputredirect
MQPROGS = mqueuewriter mqueuereader mymqueuewriter

# the message queue programs need <mqueue.h>, which only Linux has
ifeq ($(shell uname),Linux)
PROGS += $(MQPROGS)
endif

all: $(PROGS)

$(MQPROGS): %: %.c
	$(CC) $(CFLAGS) $< -o $@ -lrt

%: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f namedpipe simpleoutput outputredirect $(MQPROGS) my_pipe fifo pipeout pipeout.gz

.PHONY: all clean
