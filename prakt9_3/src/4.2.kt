import kotlin.random.Random
interface Payment {
    fun makePayment(amount: Double): Boolean
}

class BankAccount(var balance: Double) : Payment {
    override fun makePayment(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    fun cancelPayment(amount: Double) {
        balance += amount
    }
}

class BankCard(var cardNumber: String, var cvv: Int, var balance: Double) : Payment {
    override fun makePayment(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    fun blockCard() {
        println("Карта $cardNumber заблокирована")
    }
}

class Administrator {
    fun blockCardExceedingPayment(card: BankCard, amount: Double) {
        if (!card.makePayment(amount)) {
            card.blockCard()
        }
    }
}

fun main() {
    val bankAccount = BankAccount(1000.0)
    val bankCard = BankCard("1234 5678 9012 3456", 123, 500.0)
    val administrator = Administrator()
    val orderAmount = Random.nextDouble(100.0, 500.0)
    if (bankCard.makePayment(orderAmount)) {
        println("Оплата заказа на сумму $orderAmount прошла успешно")
    } else {
        println("Недостаточно средств на карте для оплаты заказа")
    }
    administrator.blockCardExceedingPayment(bankCard, 700.0)
    bankAccount.cancelPayment(orderAmount)
    println("Платёж на сумму $orderAmount был аннулирован")
}
