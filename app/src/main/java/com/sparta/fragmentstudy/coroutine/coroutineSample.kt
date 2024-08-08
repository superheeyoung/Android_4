package com.sparta.fragmentstudy.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/*
* TODO suspend function example - 동시 수행 Sample
* *//*
suspend fun random1() : Int {
    delay(1000L)
    return Random.nextInt(0,500)
}

suspend fun random2() : Int {
    delay(1000L)
    return Random.nextInt(0,500)
}


fun main() = runBlocking {
    val measureTime = measureTimeMillis {
        val value1 = async { random1() }
        val value2 = async { random2() }
        //delay 된 이후 suspend function에 결과를 가져옴
        //await 키워드를 만나면 async 블록이 수행이 끝났는지 확인하고 끝나지 않았다면 suspend 되었다가 깨어났을 때 반환값을 받아온다.
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    }
    //1000millisecond 내외로 결과값이 나옴 -> 두개의 코드를 한번에 실행시킴
    println(measureTime)
}*/
/*
* ------- TODO suspend function example -------------
* */


/*
* TODO supervisorJob example
* */

suspend fun random1(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

//TODO supervisorJob example
suspend fun random2Exception() {
    delay(500L)
    throw ArithmeticException()
}

fun main() = runBlocking {
    //TODO supervisorJob 예제
    //만약 supervisorjob없이 실행한다면 job2가 cancel이 되면 부모로 향해 cancel을 올려보내 scope가 cancel이 돼서 scope의 자식인 job1이 캔슬되는것이 일반적
    //supervisorjob은 예외가 발생했을 때 취소를 아랫방향에만 보내기 때문에 job2에 에러가 생겼을 때 job2의 자식에게만 영향을 줄 수 있고, job1에게는 영향이 없다.
    val scope = CoroutineScope(Dispatchers.IO /*+ SupervisorJob()*/) //에러를 아랫방향으로만
    val job1 = scope.launch { println(random1()) }
    val job2 = scope.launch { random2Exception() }
    //두개의 결과를 기다림
    joinAll(job1, job2)
}
