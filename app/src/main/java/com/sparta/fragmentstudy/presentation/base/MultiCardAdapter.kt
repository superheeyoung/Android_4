package com.sparta.fragmentstudy.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparta.fragmentstudy.data.database.UserEntity
import com.sparta.fragmentstudy.databinding.ItemBlueCardBinding
import com.sparta.fragmentstudy.databinding.ItemDefaultBinding
import com.sparta.fragmentstudy.databinding.ItemLightBlueCardBinding
import com.sparta.fragmentstudy.databinding.ItemOrangeCardBinding

//adapter : 아이템 단위로 view를 생성하여 recyclerView에 바인딩 시키는 역할
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
        val muiltiViewType = MultiViewEnum.entries.find { it.viewType == viewType }
        return when (muiltiViewType) {
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

            /*
            * TODO   else -> throw IllegalArgumentException("Invalid view type") 하지 말것
            * */
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

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return userList.size
    }

    //viewHolder와 data 바인딩
    //ViewHolder와 position을 인자로 받아서 holder의 데이터 변경 -> 스크롤 해서 데이터 바인딩이 필요한 만큼 호출
    //클릭 이벤트 처리
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


    //VIEWHOLDER : 화면에 표시될 아이템 뷰를 저장하는 객체
    //item layout의 ui값 뿌려주기
    /*
    * TODO : 꼭말하기
    *  inner Class는 내부 클래스로 ViewHolder를 선언하면 묵시적으로 Adapter 클래스를 참조하는것
    * inner class로 선언시 내부에 숨겨진 Outer Class(Adapter Class)를 보관하게 되고, 참조를 해제하지 못하는 경우가 생겨서 메모리 누수생김 -> 프로파일링 시 찾기 쉽지 않음
    * 코틀린은 Nested Class가 기본임
    * */
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