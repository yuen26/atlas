# Atlas

## High-level architecture

---

## Event-driven architecture

### Outbox pattern

### Saga

- Choreography (event-based) — Distribute the decision-making and sequencing among the saga participants. They primarily communicate by exchanging events.
- Orchestration (command-based) — Centralize a saga’s coordination logic in a saga orchestrator class. A saga orchestrator sends command messages to saga participants telling them which operations to perform.
