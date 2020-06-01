package com.dariushm2.stockify.model

data class TradingStatus(var status: StatusType,
                         var reason: StatusReason)

enum class StatusType {
    H, // Trading halted across all US equity markets
    O, // Trading halt released into an Order Acceptance Period (IEX-listed securities only)
    P, // Trading paused and Order Acceptance Period on IEX (IEX-listed securities only)
    T  // Trading on IEX
}

enum class StatusReason {
    T1,   // Halt News Pending
    IPO1, // IPO/New Issue Not Yet Trading
    IPOD, // IPO/New Issue Deferred
    MCB3, // Market-Wide Circuit Breaker Level 3 – Breached
    NA,   // Reason Not Available

    // Order Acceptance Period Reasons
    T2,   // Halt News Dissemination
    IPO2,  //IPO/New Issue Order Acceptance Period
    IPO3, // IPO Pre-Launch Period
    MCB1, // Market-Wide Circuit Breaker Level 1 – Breached
    MCB2  // Market-Wide Circuit Breaker Level 2 – Breached
}