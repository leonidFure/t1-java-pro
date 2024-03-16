package ru.stepup.spring.coins.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "receiver_number")
    private String receiverNumber;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "transaction_id")
    private String transactionId;
}
