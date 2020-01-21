It is a fake service "Loyalty-Points-Api".

For swagger please go the link: http://localhost:8080/
 
For database please go the link: http://localhost:8080/h2

I left test url for test start of points calculation: http://localhost:8080/points/test

Before production deployment it is necessary:
1. Fix situation when scheduler missed start at Monday (if service was down). 
As proposal: add boolean flags "isThisWeekCalculationPerformed" and add additional logic.
Method with flexible startTime is created. 
Need to discuss (with Support team and Analysts).
2. DB - all old data need to me migrated to some back-up (old = older that 5 week).
3. Add integration tests.
4. Add unit tests.
5. Update swagger information and documentation.
6. If service be used in different time zones - check zones bugs.
7. For another database type - need to change properties.