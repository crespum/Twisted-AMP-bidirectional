from twisted.protocols import amp
from twisted.internet import reactor
from twisted.internet.protocol import Factory
from prototipes import *


class Math(amp.AMP):

    def sum(self, a, b):
        total = a + b
        print 'Did a sum: %d + %d = %d' % (a, b, total)
        # Already asynchronous call
        d = self.callRemote(Substraction, a=90, b=81)

        def summed(result):
            return result['total']
        d.addCallback(summed)

        return {'total': total}
    Sum.responder(sum)


def main():
    pf = Factory()
    pf.protocol = Math
    reactor.listenTCP(1234, pf)
    print 'Server running...'
    reactor.run()

if __name__ == '__main__':
    main()
