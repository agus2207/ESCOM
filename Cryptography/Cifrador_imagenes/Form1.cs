using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Cifrador_imagenes
{
    public partial class Form1 : Form
    {
        string sFileName;
        Bitmap imagen2;
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog selector = new OpenFileDialog();
            selector.Filter = "All Files (*.*)|*.*";
            selector.FilterIndex = 1;
            selector.Multiselect = true;

            if (selector.ShowDialog() == DialogResult.OK)
            {
                sFileName = selector.FileName;
                Bitmap imagen1 = new Bitmap(sFileName, true);
                pictureBox1.Image = imagen1;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            imagen2 = new Bitmap(sFileName, true);
            for (int x = 0; x < imagen2.Width; x++)
            {
                for (int y = 0; y < imagen2.Height; y++)
                {
                    Color pixelColor = imagen2.GetPixel(x, y);
                    Color newColor = Color.FromArgb((int.Parse(textBox1.Text) + pixelColor.R) % 256, (int.Parse(textBox2.Text) + pixelColor.G) % 256, (int.Parse(textBox3.Text) + pixelColor.B) % 256);
                    imagen2.SetPixel(x, y, newColor);
                }
            }
            pictureBox2.Image = imagen2;
            imagen2.Save(@"../../image.bmp", System.Drawing.Imaging.ImageFormat.Bmp);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            imagen2 = new Bitmap(sFileName, true);
            int r = (int.Parse(textBox1.Text)) % 256;
            int g = (int.Parse(textBox2.Text)) % 256;
            int b = (int.Parse(textBox3.Text)) % 256;
            r = 256 - r;
            g = 256 - g;
            b = 256 - b;
            for (int x = 0; x < imagen2.Width; x++)
            {
                for (int y = 0; y < imagen2.Height; y++)
                {
                    Color pixelColor = imagen2.GetPixel(x, y);
                    Color newColor = Color.FromArgb((r + pixelColor.R) % 256, (g + pixelColor.G) % 256, (b + pixelColor.B) % 256);
                    imagen2.SetPixel(x, y, newColor);
                }
            }
            pictureBox2.Image = imagen2;
            imagen2.Save(@"../../imagen.bmp", System.Drawing.Imaging.ImageFormat.Bmp);
        }
    }
}
