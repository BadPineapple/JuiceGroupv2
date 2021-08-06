/**
 * Created by afrodite on 8/6/21.
 */
function profissionalAtivo() {
    var painelProfissional = document.getElementById("funciona-profissional");
    var painelPaciente = document.getElementById("funciona-paciente");

    var buttonProfissional = document.getElementById("button-profissional");
    var buttonPaciente = document.getElementById("button-paciente");

    buttonProfissional.classList.remove("button-white");
    buttonProfissional.classList.add("button-secundary");

    buttonPaciente.classList.remove("button-secundary");
    buttonPaciente.classList.add("button-white");

    painelProfissional.classList.remove('painel');
    painelProfissional.classList.add('painel-ativo');

    painelPaciente.classList.remove('painel-ativo');
    painelPaciente.classList.add('painel');



}

function pacienteAtivo() {
    var painelProfissional = document.getElementById("funciona-profissional");
    var painelPaciente = document.getElementById("funciona-paciente");

    var buttonProfissional = document.getElementById("button-profissional");
    var buttonPaciente = document.getElementById("button-paciente");

    buttonProfissional.classList.remove("button-secundary");
    buttonProfissional.classList.add("button-white");

    buttonPaciente.classList.remove("button-white");
    buttonPaciente.classList.add("button-secundary");

    painelProfissional.classList.remove('painel-ativo');
    painelProfissional.classList.add('painel');

    painelPaciente.classList.remove('painel');
    painelPaciente.classList.add('painel-ativo');
}