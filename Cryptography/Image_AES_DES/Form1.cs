using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Security.Cryptography;

namespace Image_AES_DES
{
    public partial class Form1 : Form
    {
        string sFileName;
        string skey;
        string destino;
        Image img;
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
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (checkBox1.Checked)//if checked, uncheck it
            {
                checkBox1.Checked = false;
            }
            else // if unchecked, check it
            {
                checkBox1.Checked = true;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            skey = GenerateKey();   
            if (checkBox2.Checked)
            {
                destino = @"../../imagen.bmp";
                EncryptFile(sFileName, destino, skey);
            }
            else // if unchecked, check it
            {
                //sFileName = @"../../imagen.bmp";
                destino = @"../../imagen2.bmp";
                DecryptFile(sFileName, destino, skey);
            }
        }

        private void EncryptFile(string source, string destination, string sKey)
        {
            FileStream fsInput = new FileStream(source,
                FileMode.Open,
                FileAccess.Read);

            FileStream fsEncrypted = new FileStream(destination,
                            FileMode.Create,
                            FileAccess.Write);

            DESCryptoServiceProvider DES = new DESCryptoServiceProvider();
            DES.Key = ASCIIEncoding.ASCII.GetBytes(sKey);
            DES.IV = ASCIIEncoding.ASCII.GetBytes(sKey);
            ICryptoTransform desencrypt = DES.CreateEncryptor();
            CryptoStream cryptostream = new CryptoStream(fsEncrypted,
                                desencrypt,
                                CryptoStreamMode.Write);
            //byte[] bytearrayinput = new byte[fsInput.Length - 1];
            byte[] headerBuffer = new byte[54];
            fsInput.Read(headerBuffer, 0, headerBuffer.Length);
            fsEncrypted.Write(headerBuffer, 0, headerBuffer.Length);
            //cryptostream.Write(bytearrayinput, 0, bytearrayinput.Length);
            fsInput.CopyTo(cryptostream);
            cryptostream.Close();
            fsInput.Close();
            fsEncrypted.Close();

        }

        private string GenerateKey()
        {
            // Create an instance of Symetric Algorithm. Key and IV is generated automatically.
            DESCryptoServiceProvider desCrypto = (DESCryptoServiceProvider)DESCryptoServiceProvider.Create();

            // Use the Automatically generated key for Encryption. 
            return ASCIIEncoding.ASCII.GetString(desCrypto.Key);

        }

        private void DecryptFile(string source, string destination, string sKey)
        {
            try
            {
                FileStream fsInput = new FileStream(source, FileMode.Open, FileAccess.Read);

                FileStream fsDecrypted = new FileStream(destination, FileMode.Create, FileAccess.Write);

                DESCryptoServiceProvider DES = new DESCryptoServiceProvider();
                DES.Key = ASCIIEncoding.ASCII.GetBytes(sKey);
                DES.IV = ASCIIEncoding.ASCII.GetBytes(sKey);

                ICryptoTransform desdecrypt = DES.CreateDecryptor();
                CryptoStream cryptostream = new CryptoStream(fsDecrypted, desdecrypt, CryptoStreamMode.Read);

                byte[] headerBuffer = new byte[54];

                fsInput.Read(headerBuffer, 0, headerBuffer.Length);

                fsDecrypted.Write(headerBuffer, 0, headerBuffer.Length);
                fsInput.CopyTo(cryptostream);

                cryptostream.Close();
                fsInput.Close();
                fsDecrypted.Close();
            }catch(Exception e)
            {
                
            }
        }
    }
}
