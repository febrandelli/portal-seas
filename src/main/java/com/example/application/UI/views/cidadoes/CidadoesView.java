package com.example.application.UI.views.cidadoes;

import com.example.application.backend.domain.*;
import com.example.application.backend.repository.*;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.UI.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;



@CssImport("./views/helloworld/hello-world-view.css")
@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Cidadões")
public class CidadoesView extends VerticalLayout {

    private static final String DISCARD_MESSAGE = "Há modificações não salvas, deseja discarta-las?";
    private static final String DELETE_MESSAGE = "Tem certeza que deseja excluir esse cidadão? Essas informações nao poderam ser recuperadas.";
    private EstadoRepository estadoRepository;
    private CidadeRepository cidadeRepository;
    private CorRepository corRepository;
    private SexoRepository sexoRepository;
    private MotivoRepository motivoRepository;
    private CidadaoRepository cidadaoRepository;
    private BeneficioRepository beneficioRepository;
    private FonteDeRendaRepository fonteDeRendaRepository;
    private CasosEspeciaisRepository casosEspeciaisRepository;

    @Autowired
    public CidadoesView(
            EstadoRepository estadoRepository,
            CidadeRepository cidadeRepository,
            CorRepository corRepository,
            SexoRepository sexoRepository,
            MotivoRepository motivoRepository,
            CidadaoRepository cidadaoRepository,
            BeneficioRepository beneficioRepository,
            FonteDeRendaRepository fonteDeRendaRepository,
            CasosEspeciaisRepository casosEspeciaisRepository) {

        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.corRepository = corRepository;
        this.sexoRepository = sexoRepository;
        this.motivoRepository = motivoRepository;
        this.cidadaoRepository = cidadaoRepository;
        this.beneficioRepository = beneficioRepository;
        this.fonteDeRendaRepository = fonteDeRendaRepository;
        this.casosEspeciaisRepository = casosEspeciaisRepository;

        H1 title = new H1();
        title.setText("Cidadoes");
        add(
            title,
            crudCidadao()
        );
    }

    private GridCrud<Cidadao> crudCidadao() {
        GridCrud<Cidadao> crud = new GridCrud<>(Cidadao.class);

        crud.getGrid().setColumns("nome", "dataNascimento", "casosEspeciais", "cidadeNascimento");
        crud.setFindAllOperationVisible(false);
        crud.getAddButton().setText("Adicionar");
        crud.getUpdateButton().setText("Atualizar");
        crud.getDeleteButton().setText("Apagar");
        crud.setDeletedMessage("Usuario excluido da base de dados.");

        crud.getGrid()
                .addColumn(cidadao -> cidadao.getCidadeNascimento().getIdEstado().getUF())
                .setHeader("Estado");
        crud.getGrid().addColumn(
                new ComponentRenderer<>(
                        cidadao -> {
                            Checkbox checkbox = new Checkbox();
                            checkbox.setValue( cidadao.getQuerSairDasRuas() );
                            return checkbox;
                        }
                )
        ).setHeader("Quer sair da rua");

        crud.getCrudFormFactory().setVisibleProperties("nome", "dataNascimento", "sexo", "cor", "fonteDeRenda",
                "querSairDasRuas", "motivos", "casosEspeciais", "beneficios", "cidadeNascimento");

        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "nome", "dataNascimento", "sexo", "cor", "fonteDeRenda",
                "querSairDasRuas", "motivos", "casosEspeciais", "beneficios", "cidadeNascimento");

        crud.getCrudFormFactory().setFieldProvider("sexo",
                new ComboBoxProvider<>(sexoRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("cor",
                new ComboBoxProvider<>(corRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("fonteDeRenda",
                new ComboBoxProvider<>(fonteDeRendaRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("motivos",
                new CheckBoxGroupProvider<>(motivoRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("casosEspeciais",
                new CheckBoxGroupProvider<>(casosEspeciaisRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("beneficios",
                new CheckBoxGroupProvider<>(beneficioRepository.findAll()));

        crud.getCrudFormFactory().setFieldProvider("cidadeNascimento",
                new ComboBoxProvider<>(cidadeRepository.findAll()));

        crud.setOperations(
                () -> cidadaoRepository.findAll(),
                cidadao -> cidadaoRepository.save(cidadao),
                cidadao -> cidadaoRepository.save(cidadao),
                cidadao -> cidadaoRepository.delete(cidadao)
        );
        return crud;
    }

}
