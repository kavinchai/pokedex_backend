package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*


@Entity
@Table(name = "trainer_table") // All tables need to be named with _ notation
data class Trainer(
    @Id
    @Column(name = "trainerid") // All names in @Column need to be lowercase
    val trainerid: Int,

)


