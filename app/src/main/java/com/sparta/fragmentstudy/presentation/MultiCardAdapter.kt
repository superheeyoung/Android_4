package com.standard.multiviewtyperecyclerview.presentation.main.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparta.fragmentstudy.data.UserEntity
import com.sparta.fragmentstudy.databinding.ItemBlueCardBinding
import com.sparta.fragmentstudy.databinding.ItemDefaultBinding
import com.sparta.fragmentstudy.databinding.ItemLightBlueCardBinding
import com.sparta.fragmentstudy.databinding.ItemOrangeCardBinding
import com.sparta.fragmentstudy.presentation.MultiViewEnum

//클릭 이벤트 처리 람다함수 파라메터로 사용
class MultiCardAdapter(private val onClick: (UserEntity) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userList = listOf<UserEntity>()

    //TODO
    //viewholder 생성
    //ViewHolder에 연결된 view 생성, 초기화
    //multi view type 처리
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //multi view type을 구현하는 item layout 연결
        //enum ordinal값 사용 보단 enum의 entries(enum의 list를 뽑아서 return)를 뽑아서 사용
        //sealed class
        val MuiltiViewType = MultiViewEnum.entries.find { it.viewType == viewType }
        return when (MuiltiViewType) {
            MultiViewEnum.BLUE -> {
                val binding =
                    ItemBlueCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueTypeViewHolder(binding)
            }

            MultiViewEnum.LIGHTBLUE -> {
                val binding =
                    ItemLightBlueCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                LightBlueTypeViewHolder(binding)
            }

            MultiViewEnum.ORANGE -> {
                val binding =
                    ItemOrangeCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                OrangeTypeViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemDefaultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                UnknownViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    //viewHolder와 data 바인딩
    //클릭 이벤트 처리
    //TODO
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = userList[position]
        when (holder) {
            is BlueTypeViewHolder -> {
                val blueHolder = holder as BlueTypeViewHolder
                blueHolder.bind(currentItem)

                holder.itemView.setOnClickListener {
                    onClick(currentItem)
                }
            }

            is LightBlueTypeViewHolder -> {
                val lightBlueHolder = holder as LightBlueTypeViewHolder
                lightBlueHolder.bind(currentItem)

                holder.itemView.setOnClickListener {
                    onClick(currentItem)
                }
            }

            is OrangeTypeViewHolder -> {
                val orangeHolder = holder as OrangeTypeViewHolder
                orangeHolder.bind(currentItem)

                holder.itemView.setOnClickListener {
                    onClick(currentItem)
                }
            }
        }
    }

    //아이템의 위치(position)에 따라 어떤 뷰 타입을 가져야하는지 결정
    //position 즉 아이템의 위치에 접근하여 아이템의 뷰타입 결정
    override fun getItemViewType(position: Int): Int {
        return userList[position].cardType.viewType
    }

    //item layout의 ui값 뿌려주기
    class BlueTypeViewHolder(private val binding: ItemBlueCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvUserName.text = user.name
                tvCardNum.text = user.cardNumber
                tvCardPeriod.text = user.cardPeriod
                tvBalance.text = user.balance.toString()
            }
        }
    }

    class LightBlueTypeViewHolder(private val binding: ItemLightBlueCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvUserName.text = user.name
                tvCardNum.text = user.cardNumber
                tvCardPeriod.text = user.cardPeriod
                tvBalance.text = user.balance.toString()
            }
        }
    }

    class OrangeTypeViewHolder(private val binding: ItemOrangeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvUserName.text = user.name
                tvCardNum.text = user.cardNumber
                tvCardPeriod.text = user.cardPeriod
                tvBalance.text = user.balance.toString()
            }
        }
    }

    //TODO
    //Enum외의 data가 왔을 때(server or android 개발자) 대응
    class UnknownViewHolder(
        binding: ItemDefaultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = Unit
    }
}