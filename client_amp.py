from twisted.internet import reactor
from twisted.internet.endpoints import TCP4ClientEndpoint, connectProtocol
from twisted.protocols import amp
from prototipes import *


class Substraction(amp.Command):
    arguments = [('a', amp.Integer()),
                 ('b', amp.Integer())]
    response = [('total', amp.Integer())]


class Math(amp.AMP):

    def substraction(self, a, b):
        total = a - b
        print 'Did a substraction: %d - %d = %d' % (a, b, total)
        return {'total': total}
    Substraction.responder(substraction)


def doMath():
    sumDeferred = connectProtocol(destination, Math())

    def connected(ampProto):
        return ampProto.callRemote(Sum, a=13, b=81)
    sumDeferred.addCallback(connected)

    def summed(result):
        return result['total']
    sumDeferred.addCallback(summed)

if __name__ == '__main__':
    destination = TCP4ClientEndpoint(reactor, '127.0.0.1', 1234)
    doMath()
    reactor.run()
