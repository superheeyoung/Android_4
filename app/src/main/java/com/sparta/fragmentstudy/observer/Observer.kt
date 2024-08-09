package com.sparta.fragmentstudy.observer

//Observer 역할을 하는 interface
interface Observer {
    fun update()
}

//관찰자 : Observer 인터페이스 구현
class Dog : Observer {
    override fun update() {
        println("멍멍")
    }
}

class Cat : Observer {
    override fun update() {
        println("야옹")
    }
}

//Owner클래스들은 Observer 클래스인 고양이 강아지 클래스에 update를 알리기 위해 notifyUpdate() 함수
class Owner {
    val observerList = mutableListOf<Observer>()
    fun register(observer: Observer) {
        observerList.add(observer)
    }

    fun notifyUpdate() {
        for (observer in observerList) {
            observer.update()
        }
    }
}

//Observer 패턴의 핵심 기능인 Observer들을 등록하고 notifyUpdate() 함수를 통한 옵져버들을 update()한다는 것이 핵심
fun main() {
    val owner = Owner()
    val dog = Dog()
    val cat = Cat()

    owner.register(dog)
    owner.register(cat)

    owner.notifyUpdate()
}
