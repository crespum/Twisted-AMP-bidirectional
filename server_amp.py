from twisted.protocols import amp
from twisted.internet import reactor
from twisted.internet.protocol import Factory
from commands import *


class Math(amp.AMP):

    def sum(self, a, b):
        total = a + b
        print 'I did a sum: %d + %d = %d' % (a, b, total)

        d = self.callRemote(Substraction, a=10, b=1)
        def subs_res(result):
            print 'Remote did a substraction: 10 - 1 = ' + str(result['total'])
            return result['total']
        d.addCallback(subs_res)
        def subs_error(err):
            print err
        d.addErrback(subs_error)
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
