<?php
try {
    // something
}catch(          ExceptionType1|    ExceptionType2        $e) {
echo $e->getTraceAsString();
        } catch (   ExceptionType3 |    ExceptionType4   
            |ExceptionType5 $e) {
echo $e->getTraceAsString();
} finally {
    // test
}
