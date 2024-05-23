package co.com.sofkau.stepDefinitions.web;

import co.com.sofkau.stepDefinitions.SetUp;
import co.com.sofkau.tasks.AgregarProducto;
import co.com.sofkau.tasks.BuscarPorTexto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static co.com.sofkau.questions.BotonConfirmarCompra.botonConfirmarCompra;
import static co.com.sofkau.questions.CantidadLibrosAgregados.cantidadLibrosAgregados;
import static co.com.sofkau.tasks.IrAlCarrito.irAlCarrito;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class AgregarProductosStepDefinition extends SetUp {
    int librosAgregados;


    @When("agrega los siguientes productos al carrito")
    public void agregaLosSiguientesProductosAlCarrito(List<String> productsName) {
        librosAgregados = productsName.size();
        productsName.forEach(
                productName -> {
                    theActorInTheSpotlight().attemptsTo(
                            BuscarPorTexto.con(productName),
                            AgregarProducto.agregarProducto()
                    );
                }
        );

    }

    @When("se dirije al carrito")
    public void seDirijeAlCarrito() {
        theActorInTheSpotlight().attemptsTo(
                irAlCarrito()
        );
    }

    @Then("deberia ver los libros registrados")
    public void deberiaVerLosLibrosRegistrados() {
        theActorInTheSpotlight().should(
                seeThat(cantidadLibrosAgregados(), equalTo(librosAgregados)),
                seeThat(botonConfirmarCompra(), equalTo(Boolean.TRUE))
        );
    }


}