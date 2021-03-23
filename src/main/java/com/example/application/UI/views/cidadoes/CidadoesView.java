package com.example.application.UI.views.cidadoes;

import com.example.application.backend.domain.*;
import com.example.application.backend.repository.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.UI.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CssImport("./views/helloworld/hello-world-view.css")
@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Cadastro Abordado")
public class CidadoesView extends VerticalLayout {


    private EstadoRepository estadoRepository;
    private CidadeRepository cidadeRepository;
    private CorRepository corRepository;
    private SexoRepository sexoRepository;
    private MotivoRepository motivoRepository;
    private CidadaoRepository cidadaoRepository;

    @Autowired
    public CidadoesView(
            EstadoRepository estadoRepository,
            CidadeRepository cidadeRepository,
            CorRepository corRepository,
            SexoRepository sexoRepository,
            MotivoRepository motivoRepository,
            CidadaoRepository cidadaoRepository) {

        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.corRepository = corRepository;
        this.sexoRepository = sexoRepository;
        this.motivoRepository = motivoRepository;
        this.cidadaoRepository = cidadaoRepository;

        H1 title = new H1();
        title.setText("Cidadoes");
        add(
                title,
                crudCidadao()
        );
    }

    private Crud<Cidadao> crudCidadao(){
        Grid<Cidadao> grid = new Grid<>(Cidadao.class);
        Crud<Cidadao> crud = new Crud<>(Cidadao.class, grid, createAbordadoEditor());
        ListDataProvider<Cidadao> dataProvider = new ListDataProvider<>(cidadaoRepository.findAll());

        crud.setDataProvider(dataProvider);
        crud.addSaveListener(e -> cidadaoRepository.save(e.getItem()));
        crud.addDeleteListener(e -> System.out.println("Cancelou"));

        crud.getGrid().removeColumnByKey("id");
        crud.addThemeVariants(CrudVariant.NO_BORDER);

        return crud;
    }

    private CrudEditor<Cidadao> createAbordadoEditor() {
        TextField nomeAbordado = new TextField("Nome abordado: ");
        DatePicker dataNascimento = new DatePicker("Data de Nascimento: ");
        ComboBox<Sexo> sexo = new ComboBox<>("sexo");
        ComboBox<Cor> cor = new ComboBox<>("Cor");
        ComboBox<Estado> localNascimentoUF = new ComboBox<>("UF nascimento");
        ComboBox<Cidade> localNascimentoCidade = new ComboBox<>("Cidade nascimento");
        MultiselectComboBox<Motivo> motivoQueLevouParaRua = new MultiselectComboBox<>("Motivo que levou para rua");

        FormLayout formLayout = new FormLayout(nomeAbordado,dataNascimento,sexo,cor,localNascimentoUF,localNascimentoCidade);

        List<Sexo> sexoList = sexoRepository.findAll();
        sexo.setItemLabelGenerator(Sexo::getNomeclatura);
        sexo.setItems(sexoList);

        List<Cor> corList = corRepository.findAll();
        cor.setItemLabelGenerator(Cor::getNomeclatura);
        cor.setItems(corList);

        List<Estado> estados = estadoRepository.findAll();
        localNascimentoUF.setItemLabelGenerator(Estado::getUF);
        localNascimentoUF.setItems(estados);

        localNascimentoUF.addValueChangeListener(event -> {
            Optional<List<Cidade>> cidades = cidadeRepository.findByIdEstadoEquals(event.getValue());
            localNascimentoCidade.setItemLabelGenerator(Cidade::getNome);
            localNascimentoCidade.setItems(cidades.get());
        });

        List<Motivo> motivos = motivoRepository.findAll();
        motivoQueLevouParaRua.setItemLabelGenerator(Motivo::getDescricao);
        motivoQueLevouParaRua.setItems(motivos);

        Binder<Cidadao> binder = new Binder<>(Cidadao.class);
        binder.bind(nomeAbordado, Cidadao::getNome, Cidadao::setNome);
        binder.bind(dataNascimento, Cidadao::getDataNascimento, Cidadao::setDataNascimento);
        binder.bind(sexo, Cidadao::getSexo, Cidadao::setSexo);
        binder.bind(cor, Cidadao::getCor, Cidadao::setCor);
        binder.bind(localNascimentoCidade, Cidadao::getCidadeNascimento, Cidadao::setCidadeNascimento);

        return new BinderCrudEditor<>(binder, formLayout);
    }

}
