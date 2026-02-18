package com.fichaexpress.back_end.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FichaAbyssal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Lob
    private String imagemPersonagem;

    private String personagem;

    private String origem;

    private Integer nex;
    private Integer ne;

    private Integer atributoAgilidade;
    private Integer atributoForca;
    private Integer atributoPresenca;
    private Integer atributoInteligencia;
    private Integer atributoVigor;

    //NOVO
    private Integer modificadorAgilidade = (this.atributoAgilidade - 1) * 2;
    private Integer modificadorForca = (this.atributoForca - 1) * 2;
    private Integer modificadorPresenca = (this.atributoPresenca - 1) * 2;
    private Integer modificadorInteligencia = (this.atributoInteligencia - 1) * 2;
    private Integer modificadorVigor = (this.atributoVigor - 1) * 2;

    @Enumerated(EnumType.STRING)
    private ClassesOrdemParanormal classes;

    private boolean Subclasse;

    private String trilha;

    //STATUS
    private Integer pv;
    private Integer pvAtual;
    private Integer sanidade;
    private Integer sanidadeAtual;
    private Integer pe;
    private Integer peAtual;

    private Integer dtRitual;
    private Integer limitePE;

    //NOVO (Tipo de Defesas)
    private Integer defesa;

    private Integer reflexo;

    //NOVO
    private Integer ferimentoLeveMax = 0 ;
    private Integer ferimentoLeveAtual = 0;

    //NOVO
    private Integer ferimentoGraveAtual = 0;

    //NOVO
    private Boolean feridaAtiva = false;
    private Integer feridas = 0;

    @Embedded
    private PericiasAbyssal pericias = new PericiasAbyssal();

    @ElementCollection
    @CollectionTable(name = "ficha_habilidades", joinColumns = @JoinColumn(name = "ficha_id"))
    private List<HabilidadesAbyssal> habilidades = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "ficha_rituais", joinColumns = @JoinColumn(name = "ficha_id"))
    private List<RitualAbyssal> rituais = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "ficha_inventario", joinColumns = @JoinColumn(name = "ficha_id"))
    private List<InventarioAbyssal> inventario = new ArrayList<>();

    //Dados de REDUÇÃO DE SANIDADE
    private Integer dadoSanidade = 0;

    public void dadoDeSanidade(Integer dadoSanidade) {
        Random ValorDadoSanidade = new Random();
        System.out.println(ValorDadoSanidade.nextInt(dadoSanidade));
    }

    public void atualizarStatus(){
        calcularPV(this.atributoVigor);
        calcularPE(this.atributoPresenca);
        calcularSan();
        calcularLimitePE();
        calcularDtRitual();
        
    }

    public void calcularPV(Integer atributoVigor) {
        if (this.classes == null || this.nex == null) return;

        // Aplica a trava de 5 em 5
        int nexEfetivo = (this.nex / 5) - 1;

        if (this.classes == ClassesOrdemParanormal.COMBATENTE) {
            this.pv = (20 + atributoVigor) + (nexEfetivo * (4 + atributoVigor));

        } else if (this.classes == ClassesOrdemParanormal.ESPECIALISTA) {
            this.pv = (16 + atributoVigor) + (nexEfetivo * (3 + atributoVigor));

        } else if (this.classes == ClassesOrdemParanormal.OCULTISTA) {
            this.pv = (12 + atributoVigor) + (nexEfetivo * (2 + atributoVigor));

        }
    }

    public void calcularPE(Integer atributoPresenca) {
        if (this.classes == null || this.nex == null) return;

        int nexEfetivo = (this.nex / 5) - 1 ;

        if (this.classes == ClassesOrdemParanormal.COMBATENTE) {
            this.pe = (2 + atributoPresenca) + (nexEfetivo * (2 + atributoPresenca));
        } else if (this.classes == ClassesOrdemParanormal.ESPECIALISTA) {
            this.pe = (3 + atributoPresenca) + (nexEfetivo * (3 + atributoPresenca));
        } else if (this.classes == ClassesOrdemParanormal.OCULTISTA) {
            this.pe = (4 + atributoPresenca) + (nexEfetivo * (4 + atributoPresenca));
        }
    }

    public void calcularSan() {
        if (this.classes == null || this.nex == null) return;

        int nexEfetivo = (this.nex / 5) -1 ;

        if (this.classes == ClassesOrdemParanormal.COMBATENTE) {
            this.sanidade = (12 + (nexEfetivo * 3));
        } else if (this.classes == ClassesOrdemParanormal.ESPECIALISTA) {
            this.sanidade = (16 + (nexEfetivo * 4));
        } else if (this.classes == ClassesOrdemParanormal.OCULTISTA) {
            this.sanidade = (20 + (nexEfetivo * 5));
        }
    }

    public void calcularDtRitual(){
        this.dtRitual = 10 + limitePE + this.atributoPresenca;
    }

    public void calcularLimitePE(){

        int nexEfetivo = (this.nex / 5) - 1;

        for(int i = 0; i < nexEfetivo; i++){
            limitePE++;
        }

        //REAPROVEITANDO CALCULAR O NE
        this.ne = nexEfetivo - 1 ;
    }

    //NOVO
    private void reducaoSanidadecomBaseNex(){
        if(this.nex >= 40 && this.nex<=55){
            dadoSanidade = 4;
        }else if(this.nex >= 60 && this.nex<=75){
            dadoSanidade = 6;
        } else if (this.nex >= 80 && this.nex<=95) {
            dadoSanidade =8;
        }else if (this.nex == 99) {
            dadoSanidade = 10;
        }
    }
    //NOVO
    private void calculoDefesa(){

        Integer modificadorAgilidade = (this.atributoAgilidade - 1) * 2;

        //Será inserido assim que o Player ativar o Item ou Ritual
        Integer bonusDeRitualDefesa = 0;
        Integer bonusDoItemDefesa = 0 ;

        this.defesa = modificadorAgilidade + 10 + bonusDoItemDefesa + bonusDeRitualDefesa;
    }
    //NOVO (Terminar)
    private void calculoEsquiva(){
        Integer bonusDeRitualDefesa = 0;
        Integer bonusDoItemDefesa = 0 ;

        this.reflexo = this.defesa + this.pericias.getReflexos().getBonus();
    }
    //NOVO (Terminar) (Verificar Lógica com Alay)
    private void verificaFeridasAtivas(){

        if(pv != pvAtual && feridaAtiva == true){

            Integer modificadorVigor = (this.atributoVigor - 1) * 2;

            Integer limiteDeFeridas = 3 + this.ne + modificadorVigor;

            //Atualização de Feridas Leves
            if(limiteDeFeridas >= feridas){
                ferimentoLeveAtual++;
                //Fazer um Array aonde guarda na ordem dos ferimentos leves causados para saber precisar de curado

                // Implementar Lógica de Debuff
            }

            if(ferimentoLeveAtual == 3){
                ferimentoGraveAtual++;
                //Fazer um Array aonde guarda na ordem dos ferimentos leves causados para saber precisar de curado

                // Implementar Lógica de Debuff
            }
        }
    }

    private void modificadoresAtributos(){
        //    private Integer modificadorAgilidade = (this.atributoAgilidade - 1) * 2;
//    private Integer modificadorForca = (this.atributoForca - 1) * 2;
//    private Integer modificadorPresenca = (this.atributoPresenca - 1) * 2;
//    private Integer modificadorInteligencia = (this.atributoInteligencia - 1) * 2;
 modificadorVigor = (this.atributoVigor - 1) * 2;
    }


    //NOVO (Terminar)
    private void condicoesAtiva(){
        
    }


}
