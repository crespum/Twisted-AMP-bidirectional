from twisted.internet import reactor
from twisted.internet.endpoints import TCP4ClientEndpoint, connectProtocol
from twisted.protocols import amp
from commands import *


class Math(amp.AMP):

    def substraction(self, a, b):
        total = a - b
        print 'I did a substraction: %d - %d = %d' % (a, b, total)
        return {'total': total}
    Substraction.responder(substraction)


def doMath():
    d = connectProtocol(destination, Math())
    def connected(ampProto):
        return ampProto.callRemote(Sum, a=5, b=3)
    d.addCallback(connected)
    def sum_res(result):
        print 'Remote did a sum: 5 + 3 = ' + str(result['total'])
        return result['total']
    d.addCallback(sum_res)
    def sum_error(err):
        print err
    d.addErrback(sum_error)

    def done(result):
        print 'Done with math:', result
        reactor.stop()
    d.addCallback(done)

if __name__ == '__main__':
    destination = TCP4ClientEndpoint(reactor, '127.0.0.1', 1234)
    doMath()
    reactor.run()
