package com.example.application.UI.views.cadastros;

import com.example.application.backend.domain.*;
import com.example.application.backend.repository.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.UI.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.List;
import java.util.Optional;


@CssImport("./views/helloworld/hello-world-view.css")
@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Cadastro Abordado")
public class CadastroAbordadoView extends VerticalLayout {


    private EstadoRepository estadoRepository;
    private CidadeRepository cidadeRepository;
    private CorRepository corRepository;
    private SexoRepository sexoRepository;
    private MotivoRepository motivoRepository;

    private TextField nomeAbordado;
    private DatePicker dataNascimento;
    private ComboBox<Sexo> sexo;
    private ComboBox<Cor> cor;
    private ComboBox<Estado> localNascimentoUF;
    private ComboBox<Cidade> localNascimentoCidade;
    private MultiselectComboBox<Motivo> motivoQueLevouParaRua;
    private Button salvar;

    @Autowired
    public CadastroAbordadoView(
            EstadoRepository estadoRepository,
            CidadeRepository cidadeRepository,
            CorRepository corRepository,
            SexoRepository sexoRepository,
            MotivoRepository motivoRepository) {

        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.corRepository = corRepository;
        this.sexoRepository = sexoRepository;
        this.motivoRepository = motivoRepository;

        H1 title = new H1();
        title.setText("Novo abordado");

        FormLayout formLayout = new FormLayout();

        nomeAbordado = new TextField();
        nomeAbordado.setLabel("Nome abordado: ");

        dataNascimento = new DatePicker();
        dataNascimento.setLabel("Data de Nascimento: ");

        sexo = new ComboBox<>();
        sexo.setLabel("Sexo");
        List<Sexo> sexoList = sexoRepository.findAll();
        sexo.setItemLabelGenerator(Sexo::getNomeclatura);
        sexo.setItems(sexoList);

        cor = new ComboBox<>();
        cor.setLabel("Cor");
        cor.setItems();

        localNascimentoUF = new ComboBox<>();
        localNascimentoUF.setLabel("UF nascimento");
        List<Estado> estados = estadoRepository.findAll();
        localNascimentoUF.setItemLabelGenerator(Estado::getUF);
        localNascimentoUF.setItems(estados);


        localNascimentoCidade = new ComboBox<>();
        localNascimentoCidade.setLabel("Cidade nascimento");
        localNascimentoUF.addValueChangeListener(event -> {
            Optional<List<Cidade>> cidades = cidadeRepository.findByIdEstadoEquals(event.getValue());
            localNascimentoCidade.setItemLabelGenerator(Cidade::getNome);
            localNascimentoCidade.setItems(cidades.get());
        });

        motivoQueLevouParaRua = new MultiselectComboBox<>();
        motivoQueLevouParaRua.setLabel("Motivo que levou para rua");
        List<Motivo> motivos = motivoRepository.findAll();
        motivoQueLevouParaRua.setItemLabelGenerator(Motivo::getDescricao);
        motivoQueLevouParaRua.setItems(motivos);

        salvar = new Button("SALVAR");
        salvar.setIcon(new Icon(VaadinIcon.FILE_ADD));
        salvar.setIconAfterText(Boolean.TRUE);
        salvar.setThemeName("PRIMARY");


        formLayout.add(
                nomeAbordado,
                dataNascimento,
                sexo,
                cor,
                localNascimentoUF,
                localNascimentoCidade,
                motivoQueLevouParaRua
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em",2),
                new FormLayout.ResponsiveStep("40em",3)
        );

        formLayout.setColspan(nomeAbordado, 3);
        formLayout.setColspan(localNascimentoCidade,2);
        formLayout.setColspan(motivoQueLevouParaRua, 3);

        add(
                title,
                formLayout,
                salvar);
    }

}
