using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Proyecto_Cripto
{
    public partial class Form1 : Form
    {
        System.IO.Ports.SerialPort ArduinoPort;
        string sFileName, texto = null;
        public Form1()
        {
            InitializeComponent();
            ArduinoPort = new System.IO.Ports.SerialPort();
            ArduinoPort.PortName = "COM4";
            ArduinoPort.BaudRate = 9600;
            ArduinoPort.Open();

            this.FormClosing += FrmMain_FormClosing;
            this.button1.Click += button1_Click;

            pictureBox1.Image = Image.FromFile("C:\\Users\\agust\\Downloads\\ipn.jpg");
            pictureBox2.Image = Image.FromFile("C:\\Users\\agust\\Downloads\\escom.jpg");
        }

        private void FrmMain_FormClosing(object sender, FormClosingEventArgs e)
        {
            //cerrar puerto
            if (ArduinoPort.IsOpen) ArduinoPort.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(texto == null)
                texto = textBox1.Text;

            String llave = textBox2.Text;

            if (checkBox1.Checked && !checkBox2.Checked)
            {
                try
                {
                    ArduinoPort.WriteLine("1");
                    ArduinoPort.WriteLine(llave);
                    ArduinoPort.WriteLine(texto);
                    MessageBox.Show(this, "Operacion exitosa",
                                   "Successful", MessageBoxButtons.OK,
                                   MessageBoxIcon.Asterisk);
                    texto = null;
                    llave = null;
                }
                catch (Exception)
                {
                    MessageBox.Show(this, "Algo salio mal",
                                   "Warning", MessageBoxButtons.OK,
                                   MessageBoxIcon.Warning);
                }
            }

            else if (!checkBox1.Checked && checkBox2.Checked)
            {
                try
                {
                    ArduinoPort.WriteLine("2");
                    ArduinoPort.WriteLine(texto);
                    MessageBox.Show(this, "Operacion exitosa",
                                   "Successful", MessageBoxButtons.OK,
                                   MessageBoxIcon.Asterisk);
                }
                catch (Exception)
                {
                    MessageBox.Show(this, "Algo salio mal",
                                   "Warning", MessageBoxButtons.OK,
                                   MessageBoxIcon.Warning);
                }
            }

            else if (checkBox1.Checked && checkBox2.Checked)
            {
                try
                {
                    ArduinoPort.WriteLine("1");
                    ArduinoPort.WriteLine(llave);
                    ArduinoPort.WriteLine(texto);
                    ArduinoPort.WriteLine("2");
                    ArduinoPort.WriteLine(texto);
                    MessageBox.Show(this, "Operacion exitosa",
                                   "Successful", MessageBoxButtons.OK,
                                   MessageBoxIcon.Asterisk);
                }
                catch (Exception)
                {
                    MessageBox.Show(this, "Algo salio mal",
                                   "Warning", MessageBoxButtons.OK,
                                   MessageBoxIcon.Warning);
                }
            }

            else if (!checkBox1.Checked && !checkBox2.Checked)
            {
                MessageBox.Show(this, "Seleccione alguna opcion",
                                   "Warning", MessageBoxButtons.OK,
                                   MessageBoxIcon.Warning);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            OpenFileDialog selector = new OpenFileDialog();
            selector.Filter = "All Files (*.*)|*.*";
            selector.FilterIndex = 1;
            selector.Multiselect = true;

            if (selector.ShowDialog() == DialogResult.OK)
            {
                sFileName = selector.FileName;
                texto = System.IO.File.ReadAllText(sFileName);
                texto = texto.Replace("\r\n", " ").Replace("\n", " ").Replace("\r", " ");
                System.Console.WriteLine("Contenido del archivo = {0}", texto);
            }
        }
    }
}
