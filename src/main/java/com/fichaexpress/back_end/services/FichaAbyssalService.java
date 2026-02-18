package com.fichaexpress.back_end.services;

import com.fichaexpress.back_end.entities.FichaAbyssal;
import com.fichaexpress.back_end.entities.User;
import com.fichaexpress.back_end.repositories.FichaAbyssalRepository;
import com.fichaexpress.back_end.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FichaAbyssalService {

    @Autowired
    private FichaAbyssalRepository fichaAbyssalRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public FichaAbyssal buscarFichaAbyssalPorId(Long id){
        try{
            return fichaAbyssalRepository.findById(id).orElseThrow(()-> new RuntimeException("Erro na Busca por Ficha Abyssal") );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public FichaAbyssal createFichaAbyssal(FichaAbyssal fichaAbyssal){

        User user = userRepository.findById(fichaAbyssal.getUser().getId()).orElse(null);

        fichaAbyssal.atualizarStatus();

        fichaAbyssal.setUser(user);

        user.getFichaAbyssal().add(fichaAbyssal);

        try{
            return fichaAbyssalRepository.save(fichaAbyssal);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public FichaAbyssal atualizarFichaAbyssal(Long id, FichaAbyssal fichaAbyssalAtualizado) {

        try {
            return fichaAbyssalRepository.findById(id).map(fichaAbyssal -> {

                // --- 1. Dados Básicos e Narrativos ---
                fichaAbyssal.setImagemPersonagem(fichaAbyssalAtualizado.getImagemPersonagem());
                fichaAbyssal.setPersonagem(fichaAbyssalAtualizado.getPersonagem());
                fichaAbyssal.setOrigem(fichaAbyssalAtualizado.getOrigem());

                // --- 2. Classes e Evolução ---
                fichaAbyssal.setClasses(fichaAbyssalAtualizado.getClasses()); // Fundamental para os cálculos
                fichaAbyssal.setSubclasse(fichaAbyssalAtualizado.isSubclasse());
                fichaAbyssal.setTrilha(fichaAbyssalAtualizado.getTrilha());
                fichaAbyssal.setNex(fichaAbyssalAtualizado.getNex());
                fichaAbyssal.setNe(fichaAbyssalAtualizado.getNe());

                // --- 3. Atributos ---
                fichaAbyssal.setAtributoAgilidade(fichaAbyssalAtualizado.getAtributoAgilidade());
                fichaAbyssal.setAtributoForca(fichaAbyssalAtualizado.getAtributoForca());
                fichaAbyssal.setAtributoInteligencia(fichaAbyssalAtualizado.getAtributoInteligencia());
                fichaAbyssal.setAtributoVigor(fichaAbyssalAtualizado.getAtributoVigor());
                fichaAbyssal.setAtributoPresenca(fichaAbyssalAtualizado.getAtributoPresenca());

                // --- 4. Perícias ---
                if (fichaAbyssalAtualizado.getPericias() != null) {
                    fichaAbyssal.setPericias(fichaAbyssalAtualizado.getPericias());
                }

                // --- 5. Coleções (Habilidades, Rituais, Inventário) ---
                // Substituimos as listas antigas pelas novas vindas do JSON
                if (fichaAbyssalAtualizado.getHabilidades() != null) {
                    fichaAbyssal.getHabilidades().clear();
                    fichaAbyssal.getHabilidades().addAll(fichaAbyssalAtualizado.getHabilidades());
                }

                if (fichaAbyssalAtualizado.getRituais() != null) {
                    fichaAbyssal.getRituais().clear();
                    fichaAbyssal.getRituais().addAll(fichaAbyssalAtualizado.getRituais());
                }

                if (fichaAbyssalAtualizado.getInventario() != null) {
                    fichaAbyssal.getInventario().clear();
                    fichaAbyssal.getInventario().addAll(fichaAbyssalAtualizado.getInventario());
                }

                // --- 6. Recálculo Automático de Status Máximos ---
                // Isso garante que PV Max, PE Max, DT Ritual, etc., estejam sincronizados com Atributos/NEX
                fichaAbyssal.atualizarStatus();

                // --- 7. Status Atuais (Vida, Sanidade e Esforço ATUAIS) ---
                // O atualizarStatus mexe nos máximos, aqui salvamos o quanto o personagem tem *agora*
                if (fichaAbyssalAtualizado.getPvAtual() != null)
                    fichaAbyssal.setPvAtual(fichaAbyssalAtualizado.getPvAtual());

                if (fichaAbyssalAtualizado.getSanidadeAtual() != null)
                    fichaAbyssal.setSanidadeAtual(fichaAbyssalAtualizado.getSanidadeAtual());

                if (fichaAbyssalAtualizado.getPeAtual() != null)
                    fichaAbyssal.setPeAtual(fichaAbyssalAtualizado.getPeAtual());

                // --- 8. Defesa e Reflexos ---
                if (fichaAbyssalAtualizado.getDefesa() != null)
                    fichaAbyssal.setDefesa(fichaAbyssalAtualizado.getDefesa());

                if (fichaAbyssalAtualizado.getReflexo() != null)
                    fichaAbyssal.setReflexo(fichaAbyssalAtualizado.getReflexo());

                // --- 9. Sistema de Ferimentos (NOVO) ---
                fichaAbyssal.setFeridaAtiva(fichaAbyssalAtualizado.getFeridaAtiva());
                fichaAbyssal.setFeridas(fichaAbyssalAtualizado.getFeridas());
                fichaAbyssal.setFerimentoLeveAtual(fichaAbyssalAtualizado.getFerimentoLeveAtual());
                fichaAbyssal.setFerimentoGraveAtual(fichaAbyssalAtualizado.getFerimentoGraveAtual());
                // Nota: ferimentoLeveMax provavelmente deveria ser calculado ou setado aqui também se for variável

                return fichaAbyssalRepository.save(fichaAbyssal);
            }).orElseThrow(() -> new RuntimeException("Ficha não encontrada com ID: " + id));

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar ficha: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deletarFichaAbyssal(Long id){

        try{
            fichaAbyssalRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
