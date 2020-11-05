package bruno.koster.myeleventhapp.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bruno.koster.myeleventhapp.R
import bruno.koster.myeleventhapp.model.Money
import kotlinx.android.synthetic.main.row_money.view.*


class MoneyAdapter (
        private val moneys: List<Money>,
        private val context: Context,
        private val listener: (Money) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_money, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val money = moneys[position]

        if (holder is ViewHolder) {
            holder?.let {
                it.bind(money, position, listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return moneys.size
    }

    public fun updateList() {
        this.notifyDataSetChanged()
    }

}



class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(money: Money?, position: Int?, listener: (Money) -> Unit) {
        val rowMoneyTextView = itemView.rowMoneyTextView

        money?.let {
            rowMoneyTextView.text = "R$ ${it.money.toString()}"

            itemView.setOnClickListener { listener(money) }

        }

    }

}


